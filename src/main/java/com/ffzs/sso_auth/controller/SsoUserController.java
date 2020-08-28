package com.ffzs.sso_auth.controller;

import com.ffzs.sso_auth.dao.SsoRoleDao;
import com.ffzs.sso_auth.dao.SsoUserDao;
import com.ffzs.sso_auth.dao.SsoUserRoleDao;
import com.ffzs.sso_auth.model.SsoRole;
import com.ffzs.sso_auth.model.SsoUser;
import com.ffzs.sso_auth.model.SsoUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class SsoUserController {

    @Autowired
    private SsoUserDao ssoUserDao;

    @Autowired
    private SsoRoleDao ssoRoleDao;

    @Autowired
    private SsoUserRoleDao ssoUserRoleDao;

    @GetMapping("findUser")
    public SsoUser findUser(@RequestParam(name = "username") String username) {
        return ssoUserDao.findTopByUsername(username);
    }


    @PostMapping("register")
    public String add(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("role") String role
    ) {

        SsoUser user = ssoUserDao.save(new SsoUser(username, new BCryptPasswordEncoder().encode(password)));
        SsoRole ru = ssoRoleDao.findTopByName(role);

        if (ru != null)
            ssoUserRoleDao.save(new SsoUserRole(user.getId(), ru.getId()));
        else {
            SsoRole retru = ssoRoleDao.save(new SsoRole(role));
            ssoUserRoleDao.save(new SsoUserRole(user.getId(), retru.getId()));
        }

        return "已经成功添加用户：　" + username;
    }
}
