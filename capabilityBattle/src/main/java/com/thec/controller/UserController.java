package com.thec.controller;

import com.thec.dto.UserDto;
import com.thec.entity.LoginUser;
import com.thec.entity.Result;
import com.thec.entity.User;
import com.thec.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Paths.*;

/**
 * @author thec
 */
@Slf4j
@RestController
@Api(tags = "用户接口")
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;


    @ApiOperation("登录接口")
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }

    @ApiOperation("注册接口")
    @PostMapping("/register")
    public Result register(@RequestBody User user){return userService.register(user);}

    @ApiOperation("菜单接口")
    @GetMapping("/navData")
    public Result navData() {
        return userService.navData();
    }

    @ApiOperation("注销接口")
    @GetMapping("/logout")
    public Result logout() {
        return userService.logout();
    }

    @ApiOperation("请求用户信息")
    @GetMapping("/userInfo")
    public Result userInfo() {
        return userService.userInfo();
    }

    @ApiOperation("请求用户信息")
    @GetMapping("/userInfo/{id}")
    public Result userInfoById(@PathVariable("id") Integer id) {
        return userService.userInfoById(id);
    }

    @ApiOperation("上传用户头像")
    @PostMapping("/uploadAvatar")
    public Result uploadAvatar(@RequestParam("file") MultipartFile file) {
        try {
            return userService.uploadAvatar(file);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


}
