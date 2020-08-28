package com.ffzs.sso_auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: ffzs
 * @Date: 2020/7/18 上午10:24
 */

@Entity
@Table(name = "sso_user_role")
public class SsoUserRole {

    @Id
    @Column(name = "user_id")
    private long userId;
    @Column(name = "role_id")
    private long roleId;

    public SsoUserRole() {
    }

    public SsoUserRole(long userId, long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

}
