package com.ffzs.sso_auth.dao;

import com.ffzs.sso_auth.model.SsoRole;
import org.springframework.data.repository.CrudRepository;


public interface SsoRoleDao extends CrudRepository<SsoRole, Long> {
    SsoRole findTopByName(String name);
}
