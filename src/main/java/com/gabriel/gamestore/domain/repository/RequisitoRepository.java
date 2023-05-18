package com.gabriel.gamestore.domain.repository;

import com.gabriel.gamestore.domain.model.Requisito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequisitoRepository extends JpaRepository<Requisito, Long> {

    @Query("FROM Requisito r WHERE jogo.id = :jogo AND id = :requisito")
    Optional<Requisito> findById(@Param("jogo") Long jogoId, @Param("requisito") Long requisitoId);
}
