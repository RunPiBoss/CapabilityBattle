package com.thec.service;

import com.thec.entity.Result;
import com.thec.entity.Title;

/**
 * @author thec
 */
@SuppressWarnings("all")
public interface CompetitionService {
    Result<String> createCompetitionRoom();

    Result<String> joinCompetitionRoom(long roomId);

    Result<String> prepare(long roomId);

    Result<Title> start(long roomId);

    Result submit(long roomId, String code,String codeType);

    Result quitCompetitionRoom(long roomId);
}
