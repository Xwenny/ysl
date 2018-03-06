package com.wenny.ysl.rest.service;

import com.wenny.ysl.domain.TaotaoResult;

public interface RedisService {
    TaotaoResult synContent(long contentCid);
}
