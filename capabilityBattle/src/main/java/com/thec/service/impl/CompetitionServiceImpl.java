package com.thec.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thec.config.RedisCache;
import com.thec.dto.TitleDto;
import com.thec.entity.*;
import com.thec.mapper.HistoryMapper;
import com.thec.mapper.TestDataMapper;
import com.thec.mapper.TitleMapper;
import com.thec.service.CompetitionService;
import com.thec.service.HistoryService;
import com.thec.socket.WebSocket;
import com.thec.utils.ReviewTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

import static com.thec.utils.SystemConstants.COMPETITION_ROOM_PREFIX;

/**
 * @BelongsProject: capabilityBattle
 * @BelongsPackage: com.thec.service.impl
 * @Author: thec
 * @CreateTime: 2023-04-25  19:31
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class CompetitionServiceImpl extends ServiceImpl<TitleMapper, Title> implements CompetitionService {
    @Resource
    RedisCache redisCache;
    @Resource
    TitleMapper titleMapper;
    @Resource
    TestDataMapper testDataMapper;
    @Resource
    HistoryService historyService;
    @Resource
    WebSocket webSocket;


    ExecutorService executor = Executors.newSingleThreadExecutor();

    private static final int MAX_TITLE = 4;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Result<String> createCompetitionRoom() {
        //生成房间号并判断房间号是否已经在使用
        String roomId = RandomUtil.randomNumbers(6);
        String key = COMPETITION_ROOM_PREFIX + roomId;
        while (redisCache.existsKey(key)) {
            roomId = RandomUtil.randomNumbers(6);
            key = COMPETITION_ROOM_PREFIX + roomId;
        }

        Map<String, String> map = new HashMap<>();
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        map.put("playerOne", user.getUser().getId().toString());
        map.put("num", "0");
        redisCache.setCacheMap(key, map);
        redisCache.expire(key, 30, TimeUnit.MINUTES);
        return new Result<>(200, "创建成功", roomId);
    }

    @Override
    @SuppressWarnings("all")
    public Result<String> joinCompetitionRoom(long roomId) {
        String key = COMPETITION_ROOM_PREFIX + roomId;
        if (!redisCache.existsKey(key)) {
            return new Result<>(300, "房间号不存在或已失效");
        }
        LoginUser user = (LoginUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String userId = user.getUser().getId().toString();
        Map<String, Object> cacheMap = redisCache.getCacheMap(key);
        if (cacheMap.containsKey("playerTwo")) {
            return new Result<>(300, "房间人数已满");
        }

        String id = (String) cacheMap.get("playerOne");
        if (StrUtil.equals(id, userId)) {
            return new Result<>(300, "请勿重复加入房间");
        }


        Map<String, String> map = new HashMap<>();
        map.put("playerTwo", user.getUser().getId().toString());
        redisCache.setCacheMap(key, map);

        //通知房主有人进入房间
        WebSocket.sendMessageById("有人进入房间>" + userId, Integer.valueOf(id));


        return new Result<>(200, "加入房间成功", id);
    }

    @Override
    @SuppressWarnings("all")
    public Result<String> prepare(long roomId) {

        String key = COMPETITION_ROOM_PREFIX + roomId;
        if (!redisCache.existsKey(key)) {
            return new Result<>(300, "房间号不存在或已失效");
        }

        Map<String, Object> cacheMap = redisCache.getCacheMap(key);
        String playerOneId = (String) cacheMap.get("playerOne");
        if (!cacheMap.containsKey("prepare") || ((String) cacheMap.get("prepare")).equals("0")) {
            redisCache.setCacheMapValue(key, "prepare", "1");
            //通知房主用户已经准备
            WebSocket.sendMessageById("ready", Integer.valueOf(playerOneId));
            return new Result<>(200, "准备成功");
        }


        redisCache.setCacheMapValue(key, "prepare", "0");

        //通知房主用户取消准备
        WebSocket.sendMessageById("noReady", Integer.valueOf(playerOneId));

        return new Result<>(200, "取消准备成功");


    }

    @Override
    public Result<Title> start(long roomId) {
        String key = COMPETITION_ROOM_PREFIX + roomId;
        if (!redisCache.existsKey(key)) {
            return new Result<>(300, "房间号不存在或已失效");
        }

        Map<String, Object> cacheMap = redisCache.getCacheMap(key);
        String playerTwoId = (String) cacheMap.get("playerTwo");

        if (!cacheMap.containsKey("playerTwo")) {
            return new Result<>(300, "没有对战对象");
        }

        String playerTow = (String) cacheMap.get("prepare");

        if (playerTow.equals("0")) {
            return new Result<>(300, "玩家未全部准备");
        }

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
        redisCache.setCacheMapValue(key, "titleSet", titleSet);
        //覆盖上一次题目+存储当前题目
        redisCache.setCacheMapValue(key, "title", String.valueOf(oneTitle.getId()));
        //场次增加
        redisCache.setCacheMapValue(key, "num",
                String.valueOf(Integer.valueOf(redisCache.getCacheMapValue(key, "num")) + 1));
        //通知对面开始对战
        WebSocket.sendMessageById("对战开始·" + JSONUtil.toJsonStr(oneTitle), Integer.valueOf(playerTwoId));

        return new Result<>(200, "对战开始", oneTitle);
    }

    @Override
    @SuppressWarnings("all")
    public Result submit(long roomId, String code, String codeType) {
        String key = COMPETITION_ROOM_PREFIX + roomId;
        if (!redisCache.existsKey(key)) {
            return new Result<>(300, "房间号不存在或已失效");
        }
        LoginUser user = (LoginUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String userId = user.getUser().getId().toString();

        Map<String, Object> cacheMap = redisCache.getCacheMap(key);
        String playerOne = (String) cacheMap.get("playerOne");
        String playerTwo = (String) cacheMap.get("playerTwo");
        String titleId = (String) cacheMap.get("title");
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
        try {
            titleDto1 = submit.get();
            if (titleDto1.isSuccess()) {
                time = sdf.format(new Date());
                //通知对面我做完了
                WebSocket.sendMessageById("other success_" + time, userId.equals(playerOne)
                        ? Integer.valueOf(playerTwo) : Integer.valueOf(playerOne));
                redisCache.setCacheMapValue(key, "prepare", "0");
                if (playerOne.equals(userId)) {
                    historyService.insertHistoryByPlayerOneWin(playerOne, playerTwo, key, time);
                } else {
                    historyService.insertHistoryByPlayerTwoWin(playerOne, playerTwo, key, time);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("title", titleDto1);
        map.put("time", time);
        return new Result(200, map);
    }

    @Override
    public Result quitCompetitionRoom(long roomId) {
        LoginUser loginuser = (LoginUser) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        String key = COMPETITION_ROOM_PREFIX + roomId;
        if (!redisCache.existsKey(key)) {
            return new Result<>(300, "房间号不存在或已失效");
        }
        Map<String, Object> cacheMap = redisCache.getCacheMap(key);
        String playerOneId = (String) cacheMap.get("playerOne");
        if (!cacheMap.containsKey("playerTwo")) {
            redisCache.deleteObject(key);
            return new Result(200, "请求成功", "解散成功");
        }
        String playerTwoId = (String) cacheMap.get("playerTwo");
        Long id = loginuser.getUser().getId();
        if (Long.valueOf(playerOneId).equals(id)) {
            WebSocket.sendMessageById("房主解散了房间", Integer.valueOf(playerTwoId));
            redisCache.deleteObject(key);
        } else if (Long.valueOf(playerTwoId).equals(id)) {
            WebSocket.sendMessageById("用户退出房间", Integer.valueOf(playerOneId));
            redisCache.setCacheMapValue(key, "num", "0");
            redisCache.delCacheMapValue(key, "playerTwo");
            redisCache.setCacheMapValue(key, "prepare", "0");
            redisCache.delCacheMapValue(key, "titleSet");
        } else {
            return new Result(301, "您不属于该房间，退出失败");
        }
        return new Result(200, "请求成功", "退出成功");
    }

}
