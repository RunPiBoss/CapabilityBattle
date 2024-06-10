package com.thec.controller;

import com.thec.entity.Result;
import com.thec.entity.SubmitCode;
import com.thec.entity.Title;
import com.thec.service.LadderBattleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @BelongsProject: capabilityBattle
 * @BelongsPackage: com.thec.controller
 * @Author: thec
 * @CreateTime: 2023-05-29  15:12
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@Api(tags = "天梯对战")
@RequestMapping("/ladder")
public class LadderBattleController {

    @Resource
    LadderBattleService ladderBattleService;

    @ApiOperation("创建比赛房间")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public Result createLadderRoom() {
        return ladderBattleService.createLadderRoom();
    }

    @ApiOperation("加入比赛房间")
    @RequestMapping(value = "/join/{roomId}", method = RequestMethod.GET)
    public Result joinLadderRoom(@PathVariable("roomId") long roomId) {
        return ladderBattleService.joinLadderRoom(roomId);
    }

    @ApiOperation("准备")
    @RequestMapping(value = "/prepare/{roomId}", method = RequestMethod.GET)
    public Result<String> prepare(@PathVariable("roomId") long roomId) {
        return ladderBattleService.prepare(roomId);
    }

    @ApiOperation("退出比赛房间")
    @RequestMapping(value = "/quit/{roomId}", method = RequestMethod.GET)
    public Result<String> quitCompetitionRoom(@PathVariable("roomId") long roomId) {
        return ladderBattleService.quitLadderRoom(roomId);
    }

    @ApiOperation("开始比赛")
    @RequestMapping(value = "/start/{roomId}", method = RequestMethod.GET)
    public Result<Title> start(@PathVariable("roomId") long roomId) {
        return ladderBattleService.start(roomId);
    }

    @ApiOperation("提交代码并评测")
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Result submit(@RequestBody SubmitCode submitCode) {
        return ladderBattleService.submit(submitCode.getRoomId(),
                submitCode.getCode(), submitCode.getCodeType());
    }

}
