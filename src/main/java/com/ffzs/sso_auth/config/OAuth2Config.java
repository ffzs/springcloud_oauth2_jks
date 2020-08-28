package com.ffzs.sso_auth.config;


import com.ffzs.sso_auth.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.security.KeyPair;


/**
 * @author: ffzs
 * @Date: 2020/7/18 上午10:27
 */

@Configuration   // 不要搞成Configurable， 不然的话问题很大
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("sso_auth")
                .secret(new BCryptPasswordEncoder().encode("123456"))
                .scopes("read")
                .accessTokenValiditySeconds(60 * 60 * 24)
                .refreshTokenValiditySeconds(60 * 60 * 48)
                .authorizedGrantTypes("password", "refresh_token")
                .and()
                .withClient("sso_auth2")
                .secret(new BCryptPasswordEncoder().encode("123456"))
                .scopes("read")
                .accessTokenValiditySeconds(60 * 60 * 24)
                .refreshTokenValiditySeconds(60 * 60 * 48)
                .authorizedGrantTypes("authorization_code");
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients()
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(redisTokenStore())
                .tokenEnhancer(jwtTokenEnhancer())
                .userDetailsService(userDetailsService);
    }

    @Bean
    public TokenStore redisTokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix("sso_");
        return redisTokenStore;
    }


    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair());
        return converter;
    }

    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("ffzs-jwt.jks"), "ffzs00".toCharArray());
        return keyStoreKeyFactory.getKeyPair("ffzs-jwt");
    }
}
