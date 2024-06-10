package com.thec.service;

import com.thec.dto.HistoryQueryDto;
import com.thec.dto.LadderHistoryQueryDto;
import com.thec.entity.History;
import com.thec.entity.LadderHistory;
import com.thec.entity.Result;

import java.util.List;

/**
 * @author thec
 */
public interface LadderHistoryService {

    Result<List<LadderHistory>> searchHistory();

    Result<List<LadderHistory>> searchOneHistory(LadderHistoryQueryDto dto);
}
