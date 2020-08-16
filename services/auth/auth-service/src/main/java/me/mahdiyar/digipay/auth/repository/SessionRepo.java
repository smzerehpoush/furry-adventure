package me.mahdiyar.digipay.auth.repository;

import me.mahdiyar.base.jpa.BaseRepository;
import me.mahdiyar.digipay.auth.domain.SessionEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@Repository
public interface SessionRepo extends BaseRepository<SessionEntity> {

}
