package com.shop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.shop.domain.user.Auth.*;

@EnableWebSecurity
@RequiredArgsConstructor
class MyWebSecurity extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .mvcMatchers(HttpMethod.DELETE, "/**")
                .hasAuthority(ADMIN.auth())
                .mvcMatchers(HttpMethod.PUT, "/**")
                .hasAnyAuthority(MODERATOR.auth(), ADMIN.auth())
                .mvcMatchers(HttpMethod.POST, "/**")
                .hasAnyAuthority(MODERATOR.auth(), ADMIN.auth(), USER.auth())
                .mvcMatchers(HttpMethod.GET, "/**")
                .not().authenticated()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

}
