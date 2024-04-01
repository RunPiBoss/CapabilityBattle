package com.thec.service;

import com.thec.entity.Result;
import com.thec.entity.Title;

/**
 * @author thec
 */
@SuppressWarnings("all")
public interface LadderBattleService {

    Result submit(long roomId, String code, String codeType);

    Result createLadderRoom();

    Result joinLadderRoom(long roomId);

    Result prepare(long roomId);

    Result quitLadderRoom(long roomId);

    Result start(long roomId);
}
