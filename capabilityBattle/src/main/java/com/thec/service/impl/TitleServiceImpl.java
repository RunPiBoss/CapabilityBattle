package com.thec.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thec.entity.TestData;
import com.thec.entity.Title;
import com.thec.mapper.TestDataMapper;
import com.thec.mapper.TitleMapper;
import com.thec.service.TitleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName TitleServiceImpl
 * @Date 2023/4/25 22:37
 * @Author way
 **/
@Service
@Slf4j
public class TitleServiceImpl extends ServiceImpl<TitleMapper, Title> implements TitleService {

    @Resource
    TestDataMapper testDataMapper;

    @Override
    @Transactional
    public boolean saveTitleWithTest(Title title) {
        save(title);
        TestData testData = new TestData(title.getId(), title.getSampleInput(), title.getSampleOutput());
        testDataMapper.insert(testData);
        log.info("添加题目{}", title.getId());
        return true;
    }
}
