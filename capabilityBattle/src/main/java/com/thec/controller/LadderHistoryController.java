package com.thec.controller;

import com.thec.dto.HistoryQueryDto;
import com.thec.dto.LadderHistoryQueryDto;
import com.thec.entity.History;
<<<<<<< HEAD
import com.thec.entity.LadderHistory;
=======
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
import com.thec.entity.Result;
import com.thec.service.HistoryService;
import com.thec.service.LadderHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: capabilityBattle
 * @BelongsPackage: com.thec.controller
 * @Author: JieGe
 * @CreateTime: 2023-05-30  23:21
 * @Description: TODO
 * @Version: 1.0
 */
@Api(tags = "天梯历史记录")
@Slf4j
@RestController
@RequestMapping("/ladder")
public class LadderHistoryController {
    @Resource
    LadderHistoryService ladderHistoryService;

    @ApiOperation("查询历史记录")
    @RequestMapping(value = "/history", method = RequestMethod.GET)
<<<<<<< HEAD
    public Result<List<LadderHistory>> searchHistory() {
=======
    public Result<List<History>> searchHistory() {
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
        return ladderHistoryService.searchHistory();
    }

    @ApiOperation("查询本次对局记录")
    @RequestMapping(value = "/historyOne", method = RequestMethod.POST)
<<<<<<< HEAD
    public Result<List<LadderHistory>> searchHistory(@RequestBody LadderHistoryQueryDto dto) {
=======
    public Result<List<History>> searchHistory(@RequestBody LadderHistoryQueryDto dto) {
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
        return ladderHistoryService.searchOneHistory(dto);
    }

}
