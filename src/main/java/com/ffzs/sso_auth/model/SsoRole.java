package com.ffzs.sso_auth.model;

import javax.persistence.*;

/**
 * @author: ffzs
 * @Date: 2020/7/18 上午10:22
 */


@Entity
@Table(name = "sso_role")
public class SsoRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public SsoRole() {
    }

    public SsoRole(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
