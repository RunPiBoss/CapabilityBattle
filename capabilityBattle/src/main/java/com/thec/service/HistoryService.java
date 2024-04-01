package com.thec.service;

import com.thec.dto.HistoryQueryDto;
import com.thec.entity.History;
import com.thec.entity.Result;

import java.util.List;

@SuppressWarnings("all")
public interface HistoryService {
    Result<List<History>> searchHistory();

    Result searchOneHistory(HistoryQueryDto dto);

    Result insertHistoryByPlayerOneWin(String playerOne, String playerTwo, String roomKey, String time);

    Result insertHistoryByPlayerTwoWin(String playerOne, String playerTwo, String roomKey, String time);
}
