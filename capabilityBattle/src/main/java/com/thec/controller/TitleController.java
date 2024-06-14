package com.thec.controller;

import cn.hutool.core.bean.BeanUtil;
import com.thec.dto.TitleAddRequest;
import com.thec.entity.Result;
import com.thec.entity.TestData;
import com.thec.entity.Title;
import com.thec.service.TitleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/title")
@Slf4j
public class TitleController {

    @Resource
    TitleService titleService;


    @PostMapping("/add")
    public Result<String> addTitle(@RequestBody TitleAddRequest titleAddRequest) {
        Title title = new Title();
        BeanUtil.copyProperties(titleAddRequest, title);
        boolean b = titleService.saveTitleWithTest(title);
        return b ? new Result<>(200, "添加成功") : new Result<>(400, "添加失败");
    }
}
