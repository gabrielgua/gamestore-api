package com.gabriel.gamestore.domain.repository;

import com.gabriel.gamestore.domain.model.Requisito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisitoRepository extends JpaRepository<Requisito, Long> {
}
