package com.thec.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: capabilityBattle
 * @BelongsPackage: com.thec.controller
 * @Author: thec
 * @CreateTime: 2023-04-23  20:58
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@Slf4j
@Api(tags = "测试接口")
public class TestController {

    @PreAuthorize("hasAuthority('cbtem:index')")
    @GetMapping("/test")
    public String test(){
        return "ok";
    }
}