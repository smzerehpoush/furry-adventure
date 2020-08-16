package me.mahdiyar.digipay.user.repository;

import me.mahdiyar.base.jpa.BaseRepository;
import me.mahdiyar.digipay.user.domain.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {
    Optional<UserEntity> findFirstByUsernameEquals(String username);

    boolean existsByUsername(String username);
}
