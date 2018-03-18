package com.wenny.ysl.portal.service;

import com.wenny.ysl.domain.TbUser;

public interface UserService {
    TbUser getUserByToken(String token);
}
