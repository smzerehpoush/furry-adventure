package me.mahdiyar.digipay.auth.service.repository;

import me.mahdiyar.digipay.auth.service.domain.SessionEntity;
import me.mahdiyar.digipay.base.jpa.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Seyyed Mahdiyar Zerehpoush
 */
@Repository
public interface SessionRepo extends BaseRepository<SessionEntity> {

}
