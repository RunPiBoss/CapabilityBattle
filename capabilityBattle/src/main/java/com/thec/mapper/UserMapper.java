package com.thec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thec.entity.Menu;
import com.thec.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author thec
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取权限信息
     * @param id
     * @return
     */
    List<String> selectPermsByUserId(Long id);

    /**
     * 获取用户菜单
     * @param id
     * @return
     */
    List<Menu> selectMenuByUserId(Long id);
<<<<<<< HEAD

    void insertUser(User user);
=======
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
}
