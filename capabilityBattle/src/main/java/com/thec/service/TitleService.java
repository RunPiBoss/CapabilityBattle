package com.thec.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thec.entity.Title;

public interface TitleService extends IService<Title> {

    boolean saveTitleWithTest(Title title);
}
