package com.thec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.thec.entity.History;
import com.thec.entity.Ranking;
import com.thec.entity.Result;
import com.thec.entity.User;
import com.thec.mapper.HistoryMapper;
import com.thec.mapper.UserMapper;
import com.thec.service.HistoryService;
import com.thec.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RankingServiceImpl  implements RankingService {



    @Autowired
    private HistoryMapper historyMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public Result<List<User>> searchRanking() {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();

        queryWrapper.select(User::getId,User::getUserName,User::getNickName,User::getRating);
        List<User> users = userMapper.selectList(queryWrapper);
        return new Result<>(200,users);

    }
}
