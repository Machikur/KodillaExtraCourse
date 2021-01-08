package com.shop.domain.user;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class UserAuth implements GrantedAuthority {

    private static final UserAuth ADMIN_ROLE = new UserAuth(Auth.ADMIN);
    private static final UserAuth USER_ROLE = new UserAuth(Auth.USER);
    private static final UserAuth MODERATOR_ROLE = new UserAuth(Auth.MODERATOR);

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private Auth auth;

    private UserAuth(Auth auth) {
        this.auth = auth;
    }

    public static UserAuth of(Auth auth) {
        switch (auth) {
            case ADMIN:
                return ADMIN_ROLE;
            case MODERATOR:
                return MODERATOR_ROLE;
            default:
                return USER_ROLE;
        }
    }

    @Override
    public String getAuthority() {
        return auth.toString();
    }


}
