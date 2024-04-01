package com.thec.service;

import com.thec.dto.HistoryQueryDto;
import com.thec.dto.LadderHistoryQueryDto;
import com.thec.entity.History;
import com.thec.entity.Result;

import java.util.List;

/**
 * @author thec
 */
public interface LadderHistoryService {

    Result<List<History>> searchHistory();

    Result<List<History>> searchOneHistory(LadderHistoryQueryDto dto);
}
