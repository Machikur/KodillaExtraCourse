package com.shop.service;

import com.shop.domain.user.User;
import com.shop.domain.user.UserAuth;
import com.shop.domain.user.UserAuth.Auth;
import com.shop.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username).orElseThrow(()
                -> new UsernameNotFoundException("Nie znaleziono Użytkownika"));
    }

    public void createNewUser(String userName, String password, List<Auth> auths) {
        List<UserAuth> roleList = auths.stream()
                .map(userRoleService::createOrGetUserAuthByAuth)
                .collect(Collectors.toList());
        userRepository.save(new User(userName, passwordEncoder.encode(password), roleList));
    }

}
