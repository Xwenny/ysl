package com.wenny.ysl.rest.service;

import com.mysql.jdbc.StringUtils;
import com.wenny.ysl.dao.TbContentMapper;
import com.wenny.ysl.domain.TbContent;
import com.wenny.ysl.domain.TbContentExample;
import com.wenny.ysl.rest.dao.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.JsonUtils;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService{

    @Autowired
    private TbContentMapper contentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public List<TbContent> getContentList(long contentCid) {
        try {
            String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY,contentCid+"");
            if (!org.junit.platform.commons.util.StringUtils.isBlank(result)){
                List<TbContent> resultList = JsonUtils.jsonToList(result,TbContent.class);
                return resultList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        List<TbContent> list = contentMapper.selectByExample(example);
        try {
            String cacheString = JsonUtils.objectToJson(list);
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid + "", cacheString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
