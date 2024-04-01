package com.thec.controller;

import com.thec.entity.History;
import com.thec.entity.Ranking;
import com.thec.entity.Result;
import com.thec.entity.User;
import com.thec.service.HistoryService;
import com.thec.service.RankingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@Api(tags = "查询排名")
public class RankingController {
    @Resource
    RankingService rankingService;
    @ApiOperation("查询历史记录")
    @RequestMapping( value = "/ranking" ,method = RequestMethod.GET)
    public Result<List<User>> searchHistory() {


        return rankingService.searchRanking();

    }
}
