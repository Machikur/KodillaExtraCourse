package com.shop.configuration;

import com.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;

import static com.shop.domain.user.Auth.*;

@Component
@RequiredArgsConstructor
class StartSettings {

    private final UserService userService;

    @PostConstruct
    void startSettings() {
        userService.createNewUser("admin2", "admin2", Collections.singletonList(ADMIN));
        userService.createNewUser("user", "user", Collections.singletonList(USER));
        userService.createNewUser("admin1", "admin1", Collections.singletonList(ADMIN));
        userService.createNewUser("moderator", "moderator", Collections.singletonList(MODERATOR));
        userService.createNewUser("admin", "admin", Collections.singletonList(ADMIN));
    }

}
