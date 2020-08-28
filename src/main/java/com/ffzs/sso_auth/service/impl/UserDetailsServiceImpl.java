package com.ffzs.sso_auth.service.impl;

import com.ffzs.sso_auth.dao.SsoUserDao;
import com.ffzs.sso_auth.model.SsoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author: ffzs
 * @Date: 2020/7/18 上午10:29
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SsoUserDao ssoUserDao;

    @Autowired
    private ClientDetailsService clientDetailsService;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientDetails clientDetails = null;
        String clientSecret, role;

        try {
            clientDetails = clientDetailsService.loadClientByClientId(username);
        } catch (Exception ignored) {
        }

        if (clientDetails == null) {
            SsoUser myUser = ssoUserDao.findTopByUsername(username);
            clientSecret = myUser.getPassword();
            role = myUser.getRoles().get(0).getName();
            return User.withUsername(username)
                    .password(clientSecret)
                    .authorities(role).build();
//            return new User(username, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(role));
        }

        clientSecret = clientDetails.getClientSecret();
        if (clientSecret == null || clientSecret.trim().length() == 0) {
            clientSecret = "";
        }

        return new User(username, clientSecret, clientDetails.getAuthorities());

    }
}
