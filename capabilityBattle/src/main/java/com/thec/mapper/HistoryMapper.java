package com.thec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thec.dto.HistoryQueryDto;
import com.thec.entity.History;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author thec
 */
@Mapper
public interface HistoryMapper extends BaseMapper<History> {

    History selectOneHistory(HistoryQueryDto dto);
}