package com.thec.service;

import com.thec.dto.UserDto;
import com.thec.entity.Result;
import com.thec.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;

/**
 * @author thec
 */

public interface UserService {

    /**
     * 登录
     * @return Result
     * @param user
     */
    Result login(User user);


    /**
     * 注册
     * return Rresult
     * @param user
     */
    Result register(User user);

    /**
     * 获取菜单信息
     * @param
     * @return Result
     */
    Result navData();

    /**
     * 退出登录
     * @return Result
     */
    Result logout();

    /**
     * 用户信息
     * @return Result
     */
    Result userInfo();

    /**
     * Idchaxun用户信息
     * @return Result
     */
    Result userInfoById(Integer id);

    /**
     * 上传图片
     * @return Result
     * @Param MultipartFile
     */
    Result uploadAvatar(MultipartFile multipartFile) throws URISyntaxException;

    /**
     * 更新用户信息
     * @return Result
     * @Param user
     */
    Result updateUserInfo(User user);
}
