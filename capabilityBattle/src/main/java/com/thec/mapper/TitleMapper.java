package com.thec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thec.entity.Title;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TitleMapper extends BaseMapper<Title> {
    Title getOneTitle();
}
