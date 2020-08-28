package com.ffzs.sso_auth.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author: ffzs
 * @Date: 2020/7/18 上午10:18
 */
@Entity
@Table(name = "sso_user")
public class SsoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String password;
    private String username;

    @ManyToMany(
            targetEntity = SsoRole.class,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER  // 这里需要EAGER ，因为 lazy的话 hibernate 获取数据会有问题 failed to lazily initialize a collection of role
    )
    @JoinTable(
            name = "sso_user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    @OrderBy("id")
    private List<SsoRole> roles;


    public SsoUser() {
    }

    public SsoUser(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public SsoUser(String password, String username, List<SsoRole> roles) {
        this.password = password;
        this.username = username;
        this.roles = roles;
    }

    public List<SsoRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SsoRole> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "SsoUser{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
}

