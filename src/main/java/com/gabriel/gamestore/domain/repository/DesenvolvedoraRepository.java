package com.gabriel.gamestore.domain.repository;

import com.gabriel.gamestore.domain.model.Desenvolvedora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesenvolvedoraRepository extends JpaRepository<Desenvolvedora, Long> {
}
