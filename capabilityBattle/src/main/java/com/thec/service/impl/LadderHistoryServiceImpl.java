package com.thec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.thec.config.RedisCache;
import com.thec.dto.HistoryQueryDto;
import com.thec.dto.LadderHistoryQueryDto;
import com.thec.entity.History;
import com.thec.entity.LadderHistory;
import com.thec.entity.LoginUser;
import com.thec.entity.Result;
import com.thec.mapper.HistoryMapper;
import com.thec.mapper.LadderHistoryMapper;
import com.thec.mapper.UserMapper;
import com.thec.service.LadderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: capabilityBattle
 * @BelongsPackage: com.thec.service.impl
 * @Author: JieGe
 * @CreateTime: 2023-05-30  23:24
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class LadderHistoryServiceImpl implements LadderHistoryService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private LadderHistoryMapper ladderHistoryMapper;

    @Override
    /**
    * 只有一个getMyId--MyId参数，与MybatisPlus中的属性名和dto对象属性名，实体类属性名一致，只传递了一个参数，只根据这个查
    * 所以控制台的sql语句只有一个参数，而xml中有几个参数
    */
    public Result<List<LadderHistory>> searchHistory() {
        LambdaQueryWrapper<LadderHistory> queryWrapper = new LambdaQueryWrapper<>();
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        queryWrapper.eq(LadderHistory::getMyId, user.getUser().getId());
        List<LadderHistory> histories = ladderHistoryMapper.selectList(queryWrapper);
        return new Result<>(200, histories);
    }

    @Override
    public Result<List<LadderHistory>> searchOneHistory(LadderHistoryQueryDto dto) {
        return new Result(200,"请求成功",ladderHistoryMapper.selectOneHistory(dto));
    }
}
