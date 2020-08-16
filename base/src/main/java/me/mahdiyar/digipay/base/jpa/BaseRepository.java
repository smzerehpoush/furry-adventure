package me.mahdiyar.digipay.base.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity<T>> extends JpaRepository<T, String> {
}