package com.ffzs.sso_auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author: ffzs
 * @Date: 2020/7/18 上午10:58
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
                        "/swagger-resources", "/swagger-resources/configuration/security",
                        "/swagger-ui.html", "/css/**", "/js/**", "/images/**", "/webjars/**", "**/favicon.ico", "/index"
                );
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/rsa/token_key").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
                        "/swagger-resources", "/swagger-resources/configuration/security",
                        "/swagger-ui.html", "/course/coursebase/**").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .csrf().disable();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
