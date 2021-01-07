package com.shop.repository.user;

import com.shop.domain.user.UserAuth;
import com.shop.domain.user.UserAuth.Auth;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserAuth, Long> {

    Optional<UserAuth> findByAuth(Auth auth);

    UserAuth save(UserAuth userAuth);
}
