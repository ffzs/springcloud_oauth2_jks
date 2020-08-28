package com.ffzs.sso_auth.dao;

import com.ffzs.sso_auth.model.SsoUser;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: ffzs
 * @Date: 2020/7/18 上午10:30
 */
public interface SsoUserDao extends CrudRepository<SsoUser, Long> {
    SsoUser findTopByUsername(String username);
}
