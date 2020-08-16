package me.mahdiyar.digipay.user.service.repository;

import me.mahdiyar.digipay.base.jpa.BaseRepository;
import me.mahdiyar.digipay.user.service.domain.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {
    Optional<UserEntity> findFirstByUsernameEquals(String username);

    boolean existsByUsername(String username);
}
