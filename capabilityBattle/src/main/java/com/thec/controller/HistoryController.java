package com.thec.controller;

import com.thec.dto.HistoryQueryDto;
import com.thec.entity.History;
import com.thec.entity.Result;
import com.thec.service.HistoryService;
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
 * @author thec
 */
@Api(tags = "历史记录")
@Slf4j
@RestController
public class HistoryController {

    @Resource
    HistoryService historyService;

    @ApiOperation("查询历史记录")
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public Result<List<History>> searchHistory() {
        return historyService.searchHistory();
    }

    @ApiOperation("查询本次对局记录")
    @RequestMapping(value = "/historyOne", method = RequestMethod.POST)
    public Result<List<History>> searchHistory(@RequestBody HistoryQueryDto dto) {
        return historyService.searchOneHistory(dto);
    }

}
