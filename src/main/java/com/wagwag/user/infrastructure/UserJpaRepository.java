package com.wagwag.user.infrastructure;


import com.wagwag.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User,Long> {
    Optional<User> findByOauthInfoOid(String oid);
}
