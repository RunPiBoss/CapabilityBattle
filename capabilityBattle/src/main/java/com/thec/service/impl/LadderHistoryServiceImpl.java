package com.thec.service.impl;

import com.thec.dto.HistoryQueryDto;
import com.thec.dto.LadderHistoryQueryDto;
import com.thec.entity.History;
import com.thec.entity.Result;
import com.thec.mapper.HistoryMapper;
import com.thec.mapper.LadderHistoryMapper;
import com.thec.service.LadderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private LadderHistoryMapper ladderHistoryMapper;

    @Override
    public Result<List<History>> searchHistory() {
        return null;
    }

    @Override
    public Result<List<History>> searchOneHistory(LadderHistoryQueryDto dto) {
        return new Result(200,"请求成功",ladderHistoryMapper.selectOneHistory(dto));
    }
}
