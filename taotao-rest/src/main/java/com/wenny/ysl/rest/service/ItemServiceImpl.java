package com.wenny.ysl.rest.service;

import com.wenny.ysl.dao.TbItemDescMapper;
import com.wenny.ysl.dao.TbItemMapper;
import com.wenny.ysl.dao.TbItemParamItemMapper;
import com.wenny.ysl.domain.*;
import com.wenny.ysl.rest.dao.JedisClient;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.JsonUtils;

import javax.naming.ldap.PagedResultsControl;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Value("${REDIS_ITEM_KEY}")
    private  String REDIS_ITEM_KEY;

    @Value("${REDIS_ITEM_EXPIRE}")
    private  Integer REDIS_ITEM_EXPIRE;



    @Autowired
    private JedisClient jedisClient;

    @Override
    public TaotaoResult getItemBaseInfo(long itemId) {
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":"+itemId+":base");
            if (!(null == json || json.length() == 0)){
                TbItem item = JsonUtils.jsonToPojo(json,TbItem.class);
                return TaotaoResult.ok(item);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        if(null != item){
            try {
                jedisClient.set(REDIS_ITEM_KEY + ":"+itemId+":base", JsonUtils.objectToJson(item));
                jedisClient.expire(REDIS_ITEM_KEY + ":"+itemId+":base",REDIS_ITEM_EXPIRE);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return TaotaoResult.ok(item);
    }

    @Override
    public TaotaoResult getItemDesc(long itemId) {
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":"+itemId+":desc");
            if (!StringUtils.isBlank(json)){
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(json,TbItemDesc.class);
                return TaotaoResult.ok(itemDesc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        try {
            jedisClient.set(REDIS_ITEM_KEY + ":"+itemId+":desc", JsonUtils.objectToJson(itemDesc));
            jedisClient.expire(REDIS_ITEM_KEY + ":"+itemId+":desc",REDIS_ITEM_EXPIRE);
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.ok(itemDesc);
    }

    @Override
    public TaotaoResult getItemParam(long itemId) {
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":"+itemId+":param");
            if (!StringUtils.isBlank(json)){
                TbItemParamItem paramItem = JsonUtils.jsonToPojo(json,TbItemParamItem.class);
                return TaotaoResult.ok(paramItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list!= null && list.size()>0){
            TbItemParamItem paramItem = list.get(0);
            try {
                jedisClient.set(REDIS_ITEM_KEY + ":"+itemId+":param", JsonUtils.objectToJson(paramItem));
                jedisClient.expire(REDIS_ITEM_KEY + ":"+itemId+":param",REDIS_ITEM_EXPIRE);
            }catch (Exception e){
                e.printStackTrace();
            }
            return TaotaoResult.ok(paramItem);
        }
        return TaotaoResult.build(400,"无此商品规格");
    }
}
