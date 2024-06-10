package com.thec.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thec.config.RedisCache;
import com.thec.dto.RankingDataDto;
import com.thec.dto.TitleDto;
import com.thec.entity.*;
import com.thec.mapper.LadderHistoryMapper;
import com.thec.mapper.TestDataMapper;
import com.thec.mapper.TitleMapper;
import com.thec.mapper.UserMapper;
import com.thec.service.LadderBattleService;
import com.thec.service.LadderHistoryService;
import com.thec.socket.WebSocket;
import com.thec.utils.ReviewTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

import static com.thec.utils.SystemConstants.COMPETITION_ROOM_PREFIX;
import static com.thec.utils.SystemConstants.LADDER_BATTLE_ROOM;

/**
 * @BelongsProject: capabilityBattle
 * @BelongsPackage: com.thec.service.impl
 * @Author: thec
 * @CreateTime: 2023-05-29  15:13
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@SuppressWarnings("all")
public class LadderBattleServiceImpl extends ServiceImpl<TitleMapper, Title> implements LadderBattleService {

    @Resource
    LadderHistoryMapper ladderHistoryMapper;

    @Resource
    RedisCache redisCache;

    @Resource
    TitleMapper titleMapper;

    @Resource
    WebSocket webSocket;

    @Resource
    TestDataMapper testDataMapper;

    @Autowired
    private UserMapper userMapper;

    private static final int MAX_TITLE = 4;
    public static final int MAX_PERSON_NUM = 5;
    private static final int WIN_SCORE = 20;
    private static final int LOSE_SCORE = -10;

    ExecutorService executor = Executors.newSingleThreadExecutor();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Result submit(long roomId, String code, String codeType) {
        String key = LADDER_BATTLE_ROOM + roomId;
        if (!redisCache.existsKey(key)) {
            return new Result<>(300, "房间号不存在或已失效");
        }
        LoginUser user = (LoginUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String userId = user.getUser().getId().toString();

        Map<String, Object> cacheMap = redisCache.getCacheMap(key);
        Map<String, User> players = (Map<String, User>) JSON.parse(String.valueOf(cacheMap.get("players")));
        Map<String, Boolean> readyMap = (Map<String, Boolean>) cacheMap.get("readyMap");
        Map<String, RankingDataDto> rankingMap = null;

        String titleId = (String) cacheMap.get("title");
        String ownerId = (String) cacheMap.get("owner");

        TitleDto titleDto = new TitleDto();
        LambdaQueryWrapper<Title> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Title::getId, titleId);
        Title title = this.getOne(queryWrapper);
        titleDto.setTitle(title);
        LambdaQueryWrapper<TestData> queryWrapper1 = new LambdaQueryWrapper();
        queryWrapper1.eq(TestData::getTitleId, titleId);
        List<TestData> testData = testDataMapper.selectList(queryWrapper1);
        titleDto.setTestData(testData);

        Future<TitleDto> submit = executor.submit(new ReviewTask(titleDto, code, codeType));
        TitleDto titleDto1;
        String time = "";
        User owner = userMapper.selectById(ownerId);
        try {
            titleDto1 = submit.get();
            if (titleDto1.isSuccess()) {
                //记录排行榜信息
                rankingMap = (Map<String, RankingDataDto>) cacheMap.get("rankingMap");
                if (rankingMap == null) {
                    rankingMap = new HashMap<>();
                    RankingDataDto rankingDataDto = new RankingDataDto();
                    //TODO 如果缓存没有更新可能导致显示旧头像
                    rankingDataDto.setAvatar(user.getUser().getAvatar());
                    rankingDataDto.setNickName(user.getUser().getNickName());
                    rankingDataDto.setCnt(1);
                    rankingMap.put(userId, rankingDataDto);
                } else {
                    if (rankingMap.containsKey(userId)) {
                        RankingDataDto rankingDataDto = JSON.parseObject(String.valueOf(rankingMap.get(userId))
                                , RankingDataDto.class);
                        rankingDataDto.setCnt(rankingDataDto.getCnt() + 1);
                        rankingMap.put(userId, rankingDataDto);
                    } else {
                        RankingDataDto rankingDataDto = new RankingDataDto();
                        rankingDataDto.setAvatar(user.getUser().getAvatar());
                        rankingDataDto.setNickName(user.getUser().getNickName());
                        rankingDataDto.setCnt(1);
                        rankingMap.put(userId, rankingDataDto);
                    }
                }
                redisCache.setCacheMapValue(key, "rankingMap", rankingMap);
                String rankingJson = JSONObject.toJSONString(rankingMap);
                //时间
                time = sdf.format(new Date());
                //比赛状态标志位设0
                redisCache.setCacheMapValue(key, "status", "0");
                for (String id : players.keySet()) {
                    if (!id.equals("@type")) {
                        User userSelect = userMapper.selectById(id);
                        if (userId.equals(id)) {
                            int winScore = userSelect.getRating() + WIN_SCORE;
                            ladderHistoryMapper.insert(new LadderHistory(Integer.parseInt(ownerId),
                                    Integer.parseInt(userId), Integer.parseInt(userId), time,
                                    WIN_SCORE, winScore));
                            userSelect.setRating(winScore);
                        } else {
                            int loseScore = userSelect.getRating() + LOSE_SCORE;
                            ladderHistoryMapper.insert(new LadderHistory(Integer.parseInt(ownerId),
                                    Integer.parseInt(id), Integer.parseInt(userId), time,
                                    LOSE_SCORE, loseScore));
                            userSelect.setRating(loseScore);
                        }
                        userMapper.updateById(userSelect);
                    }
                }
                if (userId.equals(ownerId)) {
                    int winScore = owner.getRating() + WIN_SCORE;
                    //房主做完了,通知其他所有人做完了
                    WebSocket.sendMessageToOther("other success%" + time + "%" + rankingJson, players);
                    for (String id : readyMap.keySet()) {
                        if (!StrUtil.equals(ownerId, id)) {
                            readyMap.put(id, false);
                        }
                    }
                    redisCache.setCacheMapValue(key, "readyMap", readyMap);
                    ladderHistoryMapper.insert(new LadderHistory(Integer.parseInt(ownerId),
                            Integer.parseInt(ownerId), Integer.parseInt(ownerId), time,
                            WIN_SCORE, winScore));
                    owner.setRating(winScore);
                } else {
                    int loseScore = owner.getRating() + LOSE_SCORE;
                    players.remove(userId);
                    //非房主做完了,通知其他房主和除了自己以外所有人做完了
                    for (String id : readyMap.keySet()) {
                        if (!StrUtil.equals(ownerId, id)) {
                            readyMap.put(id, false);
                        }
                    }
                    WebSocket.sendMessageToOther("other success%" + time + "%" + rankingJson, players);
                    WebSocket.sendMessageById("other success%" + time + "%" + rankingJson, Integer.parseInt(ownerId));
                    redisCache.setCacheMapValue(key, "readyMap", readyMap);
                    ladderHistoryMapper.insert(new LadderHistory(Integer.parseInt(ownerId),
                            Integer.parseInt(ownerId), Integer.parseInt(userId), time,
                            LOSE_SCORE, loseScore));
                    owner.setRating(loseScore);
                }
                userMapper.updateById(owner);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("title", titleDto1);
        map.put("time", time);
        if (!Objects.isNull(rankingMap)) {
            map.put("rankingMap", rankingMap);
        }
        return new Result(200, map);

    }

    @Override
    public Result createLadderRoom() {
        String roomId = RandomUtil.randomNumbers(6);
        String key = LADDER_BATTLE_ROOM + roomId;
        while (redisCache.existsKey(key)) {
            roomId = RandomUtil.randomNumbers(6);
            key = LADDER_BATTLE_ROOM + roomId;
        }

        Map<String, Object> map = new HashMap<>();
        Map<String, Boolean> readyMap = new HashMap<>();
        LoginUser user = (LoginUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        map.put("owner", user.getUser().getId().toString());
        map.put("count", "0");
        map.put("num", "0");
        //标识是否已经开始比赛
        map.put("status", "0");
        readyMap.put(user.getUser().getId().toString(), true);
        map.put("readyMap", readyMap);
        redisCache.setCacheMap(key, map);
        redisCache.expire(key, 30, TimeUnit.MINUTES);
        return new Result<>(200, "创建成功", roomId);
    }

    @Override
    public Result joinLadderRoom(long roomId) {
        String key = LADDER_BATTLE_ROOM + roomId;
        Map<String, User> users;
        if (!redisCache.existsKey(key)) {
            return new Result<>(300, "房间号不存在或已失效");
        }
        LoginUser user = (LoginUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String userId = user.getUser().getId().toString();
        Map<String, Object> cacheMap = redisCache.getCacheMap(key);
        int count = Integer.parseInt((String) cacheMap.get("count"));
        if (count >= MAX_PERSON_NUM) {
            return new Result<>(300, "房间人数已满");
        }
        String status = (String) cacheMap.get("status");
        if (StrUtil.equals(status, "1")) {
            return new Result(300, "比赛已经开始无法加入");
        }
        String id = (String) cacheMap.get("owner");
        if (StrUtil.equals(id, userId)) {
            return new Result<>(300, "请勿重复加入房间");
        }
        if (!cacheMap.containsKey("players")) {
            users = new HashMap<>();
        } else {
            users = (Map<String, User>) JSON.parse(String.valueOf(cacheMap.get("players")));
            if (users.containsKey(userId)) {
                return new Result<>(300, "请勿重复加入房间");
            }
        }
        //向房主发送消息
        webSocket.sendMessageById("有人进入房间>" + userId, Integer.valueOf(id));
        //向除自己以外所有人发送消息
        webSocket.sendMessageToOther("有人进入房间>" + userId, users);
        users.put(userId, user.getUser());
        String players = JSON.toJSONString(users);
        redisCache.setCacheMapValue(key, "players", players);
        //初始化就绪状态
        Map<String, Boolean> map = redisCache.getCacheMapValue(key, "readyMap");
        if (!StrUtil.equals(id, userId)) {
            map.put(userId, false);
        }
        redisCache.setCacheMapValue(key, "readyMap", map);
        Map<String, RankingDataDto> rankingMap = (Map<String, RankingDataDto>) cacheMap.get("rankingMap");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("owner", id);
        resultMap.put("readyMap", map);
        if (rankingMap != null) {
            resultMap.put("rankingMap",rankingMap);
        }
        return new Result(200, "加入房间成功", resultMap);
    }

    @Override
    public Result<String> prepare(long roomId) {
        String key = LADDER_BATTLE_ROOM + roomId;
        if (!redisCache.existsKey(key)) {
            return new Result<>(300, "房间号不存在或已失效");
        }
        LoginUser loginuser = (LoginUser) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        String id = loginuser.getUser().getId().toString();
        Map<String, Object> cacheMap = redisCache.getCacheMap(key);
        String ownerId = (String) cacheMap.get("owner");
        Map<String, Boolean> readyMap = (Map<String, Boolean>) cacheMap.get("readyMap");
        Map<String, User> players = (Map<String, User>) JSON.parse(String.valueOf(cacheMap.get("players")));
        if (readyMap.get(id) == false) {
            readyMap.put(id, true);
            redisCache.setCacheMapValue(key, "readyMap", readyMap);
            players.remove(id);
            webSocket.sendMessageById("ready#" + id, Integer.parseInt(ownerId));
            webSocket.sendMessageToOther("ready#" + id, players);
            return new Result(200, "准备成功", 1);
        } else {
            readyMap.put(id, false);
            redisCache.setCacheMapValue(key, "readyMap", readyMap);
            players.remove(id);
            webSocket.sendMessageById("noReady#" + id, Integer.parseInt(ownerId));
            webSocket.sendMessageToOther("noReady#" + id, players);
            return new Result(200, "取消准备成功", 0);
        }
    }

    @Override
    public Result quitLadderRoom(long roomId) {
        LoginUser loginuser = (LoginUser) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        String key = LADDER_BATTLE_ROOM + roomId;
        if (!redisCache.existsKey(key)) {
            return new Result<>(300, "房间号不存在或已失效");
        }
        Map<String, Object> cacheMap = redisCache.getCacheMap(key);
        if (!cacheMap.containsKey("players")) {
            redisCache.deleteObject(key);
            return new Result(200, "请求成功", "解散成功");
        }
        String ownerId = (String) cacheMap.get("owner");
        Map<String, User> players = (Map<String, User>) JSON.parse(String.valueOf(cacheMap.get("players")));
        Map<String, Boolean> readyMap = (Map<String, Boolean>) cacheMap.get("readyMap");
        if (ownerId.equals(loginuser.getUser().getId().toString())) {
            webSocket.sendMessageToOther("房主解散了房间", players);
            redisCache.deleteObject(key);
        } else {
            webSocket.sendMessageById("用户退出房间!" + loginuser.getUser().getId(), Integer.parseInt(ownerId));
            players.remove(String.valueOf(loginuser.getUser().getId()));
            WebSocket.sendMessageToOther("用户退出房间!" + loginuser.getUser().getId(), players);
            redisCache.delCacheMapValue(key, "players");
            redisCache.setCacheMapValue(key, "players", players);
            readyMap.remove(String.valueOf(loginuser.getUser().getId()));
            redisCache.delCacheMapValue(key, "readyMap");
            redisCache.setCacheMapValue(key, "readyMap", readyMap);
        }
        return new Result(200, "请求成功", "退出成功");
    }

    @Override
    public Result start(long roomId) {
        String key = LADDER_BATTLE_ROOM + roomId;
        if (!redisCache.existsKey(key)) {
            return new Result<>(300, "房间号不存在或已失效");
        }

        Map<String, Object> cacheMap = redisCache.getCacheMap(key);
        Map<String, User> players = (Map<String, User>) JSON.parse(String.valueOf(cacheMap.get("players")));

        int cnt = 0;
        Title oneTitle = titleMapper.getOneTitle();
        Set<Long> titleSet = new HashSet<>();
        if (redisCache.getCacheMap(key).containsKey("titleSet")) {
            titleSet = redisCache.getCacheMapValue(key, "titleSet");
            while (titleSet.contains(oneTitle.getId())) {
                if (cnt > MAX_TITLE) {
                    return new Result<>(300, "服务器错误");
                }
                oneTitle = titleMapper.getOneTitle();
                cnt++;
            }
        }
        //设置题目
        titleSet.add(oneTitle.getId());
        //比赛状态标志位设1
        redisCache.setCacheMapValue(key, "status", "1");
        redisCache.setCacheMapValue(key, "titleSet", titleSet);
        //覆盖上一次题目+存储当前题目
        redisCache.setCacheMapValue(key, "title", String.valueOf(oneTitle.getId()));
        //场次增加
        redisCache.setCacheMapValue(key, "num",
                String.valueOf(Integer.valueOf(redisCache.getCacheMapValue(key, "num")) + 1));
        //通知对面开始对战
        WebSocket.sendMessageToOther("对战开始$" + JSONUtil.toJsonStr(oneTitle), players);

        return new Result<>(200, "对战开始", oneTitle);
    }


}
