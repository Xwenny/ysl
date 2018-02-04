package com.wenny.ysl.service;

import com.wenny.ysl.domain.TaotaoResult;
import com.wenny.ysl.domain.TbItemParam;

public interface ItemParamService {
    TaotaoResult getItemParamByCid(long cid);
    TaotaoResult insertItemParam(TbItemParam itemParam);

}
