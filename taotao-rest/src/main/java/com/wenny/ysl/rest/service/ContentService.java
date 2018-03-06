package com.wenny.ysl.rest.service;

import com.wenny.ysl.domain.TbContent;

import java.util.List;

public interface ContentService {
    List<TbContent> getContentList(long contentCid);
}
