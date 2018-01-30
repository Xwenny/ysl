package com.wenny.ysl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wenny.ysl.dao.TbItemMapper;
import com.wenny.ysl.domain.TbItem;
import com.wenny.ysl.domain.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestPageHelper {

    @Test
    public void testPageHelper(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
        TbItemExample example = new TbItemExample();
        PageHelper.startPage(2,10);
        List<TbItem> list = mapper.selectByExample(example);
        for (TbItem tbItem:list){
            System.out.println(tbItem.getTitle());
        }
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        System.out.println("共有商品："+total);
    }

}
