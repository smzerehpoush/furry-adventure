package me.mahdiyar.digipay.user.repository;

import me.mahdiyar.base.jpa.BaseRepository;
import me.mahdiyar.digipay.user.domain.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {
    List<UserEntity> findAllByUsernameContaining(String username);

    boolean existsByUsername(String username);
}
