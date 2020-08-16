package me.mahdiyar.digipay.base.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, String> {
}
