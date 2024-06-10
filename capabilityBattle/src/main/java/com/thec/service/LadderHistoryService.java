package com.thec.service;

import com.thec.dto.HistoryQueryDto;
import com.thec.dto.LadderHistoryQueryDto;
import com.thec.entity.History;
<<<<<<< HEAD
import com.thec.entity.LadderHistory;
=======
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
import com.thec.entity.Result;

import java.util.List;

/**
 * @author thec
 */
public interface LadderHistoryService {

<<<<<<< HEAD
    Result<List<LadderHistory>> searchHistory();

    Result<List<LadderHistory>> searchOneHistory(LadderHistoryQueryDto dto);
=======
    Result<List<History>> searchHistory();

    Result<List<History>> searchOneHistory(LadderHistoryQueryDto dto);
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
}
