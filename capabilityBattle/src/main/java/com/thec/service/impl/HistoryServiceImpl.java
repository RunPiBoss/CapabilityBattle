package com.thec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thec.config.RedisCache;
import com.thec.dto.HistoryQueryDto;
import com.thec.entity.History;
import com.thec.entity.LoginUser;
import com.thec.entity.Result;
import com.thec.entity.User;
import com.thec.mapper.HistoryMapper;
import com.thec.mapper.UserMapper;
import com.thec.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private UserMapper userMapper;

    private static final int WIN_SCORE = 20;
    private static final int LOSE_SCORE = -10;

    // LambdaQueryWrapper 查询
    @Override
    public Result<List<History>> searchHistory() {
        LambdaQueryWrapper<History> queryWrapper = new LambdaQueryWrapper<>();
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        queryWrapper.eq(History::getPlayerOneId, user.getUser().getId());
        List<History> histories = historyMapper.selectList(queryWrapper);
        return new Result<>(200, histories);
    }

    @Override
    public Result searchOneHistory(HistoryQueryDto dto) {
        return new Result(200,"请求成功",historyMapper.selectOneHistory(dto));
    }

    @Override
    @SuppressWarnings("all")
    public Result insertHistoryByPlayerOneWin(String playerOne, String playerTwo,
                                        String roomKey, String time) {
        User winUser = userMapper.selectById(playerOne);

        History history = new History();
        history.setPlayerOneId(Integer.valueOf(playerOne));
        history.setPlayerTwoId(Integer.valueOf(playerTwo));
        history.setNum(Integer.valueOf(redisCache.getCacheMapValue(roomKey, "num")));
        history.setPlayAdd(WIN_SCORE);
        history.setTime(time);
        Integer ratingWin = winUser.getRating() + WIN_SCORE;
        history.setRating(ratingWin);
        historyMapper.insert(history);

        User loseUser = userMapper.selectById(playerTwo);
        history.setPlayerOneId(Integer.valueOf(playerTwo));
        history.setPlayerTwoId(Integer.valueOf(playerOne));
        history.setNum(Integer.valueOf(redisCache.getCacheMapValue(roomKey, "num")));
        history.setPlayAdd(LOSE_SCORE);
        history.setTime(time);
        Integer ratingLose = loseUser.getRating() + LOSE_SCORE;
        history.setRating(ratingLose);
        historyMapper.insert(history);

        winUser.setRating(ratingWin);
        userMapper.updateById(winUser);
        loseUser.setRating(ratingLose);
        userMapper.updateById(loseUser);
        //TODO 返回值
        return null;
    }

    @Override
    @SuppressWarnings("all")
    public Result insertHistoryByPlayerTwoWin(String playerOne, String playerTwo, String roomKey,String time) {
        User loseUser = userMapper.selectById(playerOne);

        History history = new History();
        history.setPlayerOneId(Integer.valueOf(playerOne));
        history.setPlayerTwoId(Integer.valueOf(playerTwo));
        history.setNum(Integer.valueOf(redisCache.getCacheMapValue(roomKey, "num")));
        history.setPlayAdd(LOSE_SCORE);
        history.setTime(time);
        Integer ratingLose = loseUser.getRating() + LOSE_SCORE;
        history.setRating(ratingLose);
        historyMapper.insert(history);

        User winUser = userMapper.selectById(playerTwo);
        history.setPlayerOneId(Integer.valueOf(playerTwo));
        history.setPlayerTwoId(Integer.valueOf(playerOne));
        history.setNum(Integer.valueOf(redisCache.getCacheMapValue(roomKey, "num")));
        history.setPlayAdd(WIN_SCORE);
        history.setTime(time);
        Integer ratingWin = loseUser.getRating() + WIN_SCORE;
        history.setRating(ratingWin);
        historyMapper.insert(history);

        winUser.setRating(ratingWin);
        userMapper.updateById(winUser);
        loseUser.setRating(ratingLose);
        userMapper.updateById(loseUser);
        //TODO 返回值
        return null;
    }
}
