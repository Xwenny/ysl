package com.wenny.ysl.portal.service;

import com.wenny.ysl.domain.TaotaoResult;
import com.wenny.ysl.domain.TbUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.HttpClientUtil;

@Service
public class UserServiceImpl implements UserService {
    @Value("${SSO_BASE_URL}")
    public static String SSO_BASE_URL;
    @Value("${SSO_USER_TOKEN}")
    private String SSO_USER_TOKEN;
    @Value("${SSO_PAGE_LOGIN}")
    public static String SSO_PAGE_LOGIN;

    @Override
    public TbUser getUserByToken(String token) {
        try {
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
            TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
            if (result.getStatus() == 200) {
                TbUser user = (TbUser) result.getData();
                return user;

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
