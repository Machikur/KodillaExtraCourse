package com.shop.repository.user;

import com.shop.domain.user.Auth;
import com.shop.domain.user.UserAuth;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserAuth, Long> {

    Optional<UserAuth> findByAuth(Auth auth);

}
