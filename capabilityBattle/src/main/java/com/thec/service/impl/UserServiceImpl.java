package com.thec.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.thec.config.RedisCache;
import com.thec.dto.UserDto;
import com.thec.entity.LoginUser;
import com.thec.entity.Menu;
import com.thec.entity.Result;
import com.thec.entity.User;
import com.thec.mapper.UserMapper;
import com.thec.service.UserService;
import com.thec.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
<<<<<<< HEAD
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
=======
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.thec.utils.SystemConstants.AVATAR_URL;

/**
 * @author thec
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String MENU_KEY_PREFIX = "menu:";

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;


    @Override
    public Result login(User user) {
<<<<<<< HEAD
        System.out.println("user="+user);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        System.out.println("authenticationToken="+authenticationToken);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        System.out.println("authentication="+authentication);
=======
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("登录失败");
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(id);
        redisCache.setCacheObject("login:" + id, loginUser);

        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
<<<<<<< HEAD
        System.out.println(jwt);
=======
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
        return new Result(200, "登录成功", map);
    }

    @Override
<<<<<<< HEAD
    public Result register(User user) {
        // 检查用户名、密码和昵称是否为空
        if (user.getUserName() == null || user.getUserName().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty() ||
                user.getNickName() == null || user.getNickName().isEmpty()) {
            return new Result(400, "用户名、密码和昵称不能为空");
        }
        // 检查用户是否已经存在
        User existingUser = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", user.getUserName()));
        if (existingUser != null) {
            return new Result(401, "用户名已存在");
        }

        // 对用户密码进行加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        // 设置加密后的密码到用户对象
        user.setPassword(encryptedPassword);

        // 插入用户到数据库
        userMapper.insert(user);

        return new Result(200, "注册成功");
    }


    @Override
=======
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
    public Result navData() {
        List<Menu> menus;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        menus = redisCache.getCacheList(MENU_KEY_PREFIX + loginUser.getUser().getId());
        if (menus.size() == 0) {
            menus = userMapper.selectMenuByUserId(loginUser.getUser().getId());
            if (!Objects.isNull(menus)) {
                redisCache.setCacheList(MENU_KEY_PREFIX + loginUser.getUser().getId(), menus);
            }
        }
        return new Result(200, "菜单获取成功", menus);
    }

    @Override
    public Result logout() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        Long id = loginUser.getUser().getId();

        String key = "login:" + id;
        redisCache.deleteObject(key);
        return new Result(200, "注销成功");
    }

    @Override
    public Result userInfo() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = userMapper.selectById(loginUser.getUser().getId());
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setNickName(user.getNickName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEmail(user.getEmail());
        userDto.setAvatar(user.getAvatar());
        userDto.setSex(user.getSex());
        userDto.setRating(user.getRating());
        return new Result(200, "请求成功", userDto);
    }

    @Override
    public Result userInfoById(Integer id) {
        User user = userMapper.selectById(id);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setNickName(user.getNickName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setEmail(user.getEmail());
        userDto.setAvatar(user.getAvatar());
        userDto.setSex(user.getSex());
        userDto.setRating(user.getRating());
        return new Result(200, "请求成功", userDto);
    }


    @Override
    public Result uploadAvatar(MultipartFile file) throws URISyntaxException {

        // 检查文件是否为空
        if (file.isEmpty()) {
            return new Result(300, "上传文件不能为空");
        }
        // 检查文件类型是否为图片
        if (!file.getContentType().startsWith("image")) {
            return new Result(300, "上传文件必须是图片类型");
        }
        // 保存文件到服务器
        try {
            String fileName = UUID.fastUUID().toString(true);
            String path = AVATAR_URL + fileName + ".jpg";
            File image = new File(path);
            if (!FileUtil.exist(image)) {
                FileUtil.touch(image);
            }
            byte[] bytes = file.getBytes();
            FileUtil.writeBytes(bytes, image);
            LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().
                    getAuthentication().getPrincipal();
            loginUser.getUser().setAvatar(fileName);
            updateUserInfo(loginUser.getUser());

            return new Result(200, "ok", fileName);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result(300, "头像上传失败");
        }
    }

    @Override
    public Result updateUserInfo(User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", user.getId());
        userMapper.update(user, updateWrapper);
        return new Result(200, "更新成功");
    }
}
