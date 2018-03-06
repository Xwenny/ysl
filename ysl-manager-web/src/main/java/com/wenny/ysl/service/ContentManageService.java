package com.wenny.ysl.service;

import com.wenny.ysl.domain.EUDataGridResult;

public interface ContentManageService {
    EUDataGridResult getContentById(Integer page, Integer rows,long id);
}
