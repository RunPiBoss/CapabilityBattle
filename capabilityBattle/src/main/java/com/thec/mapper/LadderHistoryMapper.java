package com.thec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thec.dto.HistoryQueryDto;
import com.thec.dto.LadderHistoryQueryDto;
import com.thec.entity.History;
import com.thec.entity.LadderHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author thec
 */
@Mapper
public interface LadderHistoryMapper extends BaseMapper<LadderHistory> {
    LadderHistory selectOneHistory(LadderHistoryQueryDto dto);
}
