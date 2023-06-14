package com.gabriel.gamestore.domain.repository;

import com.gabriel.gamestore.domain.model.Modo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModoRepository extends JpaRepository<Modo, Long> {
}
