package com.shop.domain.user;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private String password;

    @ManyToMany(targetEntity = UserAuth.class,
            cascade = CascadeType.MERGE
            , fetch = FetchType.EAGER)
    private List<UserAuth> userAuths;

    public User(String userName, String password, List<UserAuth> userAuths) {
        this.userName = userName;
        this.password = password;
        this.userAuths = userAuths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userAuths;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
