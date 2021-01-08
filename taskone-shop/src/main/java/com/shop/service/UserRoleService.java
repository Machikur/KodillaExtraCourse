package com.shop.service;

import com.shop.domain.user.Auth;
import com.shop.domain.user.UserAuth;
import com.shop.repository.user.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserAuth createOrGetUserAuthByAuth(Auth auth) {
        return userRoleRepository.findByAuth(auth)
                .orElse(userRoleRepository.save(UserAuth.of(auth)));
    }

}
