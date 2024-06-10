package com.thec.service;

import com.thec.entity.History;
import com.thec.entity.Ranking;
import com.thec.entity.Result;
import com.thec.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("all")
public interface RankingService {

    Result<List<User>> searchRanking();

}
