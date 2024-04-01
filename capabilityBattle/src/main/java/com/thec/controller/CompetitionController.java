package com.thec.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.thec.config.RedisCache;
import com.thec.entity.Result;
import com.thec.entity.SubmitCode;
import com.thec.entity.Title;
import com.thec.service.CompetitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @BelongsProject: capabilityBattle
 * @BelongsPackage: com.thec.controller
 * @Author: thec
 * @CreateTime: 2023-04-24  23:57
 * @Description: TODO
 * @Version: 1.0
 */
@Api(tags = "好友竞赛接口")
@RestController
@RequestMapping("/competition")
public class CompetitionController {


    @Resource
    CompetitionService competitionService;


    @ApiOperation("创建比赛房间")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result<String> createCompetitionRoom() {

        return competitionService.createCompetitionRoom();

    }

    @ApiOperation("加入比赛房间")
    @RequestMapping(value = "/join/{roomId}", method = RequestMethod.GET)
    public Result<String> joinCompetitionRoom(@PathVariable("roomId") long roomId) {

        return competitionService.joinCompetitionRoom(roomId);
    }

    @ApiOperation("退出比赛房间")
    @RequestMapping(value = "/quit/{roomId}", method = RequestMethod.GET)
    public Result<String> quitCompetitionRoom(@PathVariable("roomId") long roomId) {

        return competitionService.quitCompetitionRoom(roomId);
    }

    @ApiOperation("准备")
    @RequestMapping(value = "/prepare/{roomId}", method = RequestMethod.POST)
    public Result<String> prepare(@PathVariable("roomId") long roomId) {

        return competitionService.prepare(roomId);

    }

    @ApiOperation("开始比赛")
    @RequestMapping(value = "/start/{roomId}", method = RequestMethod.POST)
    public Result<Title> start(@PathVariable("roomId") long roomId) {

        return competitionService.start(roomId);

    }


    @ApiOperation("提交代码并评测")
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Result submit(@RequestBody SubmitCode submitCode) {
        return competitionService.submit(submitCode.getRoomId(),
                submitCode.getCode(), submitCode.getCodeType());
    }


    @ApiOperation("获取对局历史记录")
    @RequestMapping(value = "/history/{id}", method = RequestMethod.GET)
    public Result history(@PathVariable int id) {
        return null;
    }
}
