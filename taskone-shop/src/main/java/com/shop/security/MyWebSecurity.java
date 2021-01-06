package com.shop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.shop.security.NamesOfRoles.*;

@EnableWebSecurity
@RequiredArgsConstructor
class MyWebSecurity extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("admin")
                .and()
                .withUser("moderator").password("moderator").roles("MODERATOR")
                .and()
                .withUser("user").password("user").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .mvcMatchers(HttpMethod.DELETE, "/**")
                .hasAnyRole(ADMIN_ROLE)
                .mvcMatchers(HttpMethod.PUT, "/**")
                .hasAnyRole(MODERATOR_ROLE, ADMIN_ROLE)
                .mvcMatchers(HttpMethod.POST, "/**")
                .hasAnyRole(MODERATOR_ROLE, ADMIN_ROLE, USER_ROLE)
                .mvcMatchers(HttpMethod.GET, "/**")
                .permitAll()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .httpBasic();
    }

}
