package com.shop.configuration;

import com.shop.domain.user.UserAuth.Auth;
import com.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class StartSettings {

    private final UserService userService;

    @PostConstruct
    void startSettings() {
        userService.createNewUser("admin2", "admin2", Collections.singletonList(Auth.ADMIN));
        userService.createNewUser("user", "user", Collections.singletonList(Auth.USER));
        userService.createNewUser("admin1", "admin1", Collections.singletonList(Auth.ADMIN));
        userService.createNewUser("moderator", "moderator", Collections.singletonList(Auth.MODERATOR));
        userService.createNewUser("admin", "admin", Collections.singletonList(Auth.ADMIN));
    }

}
