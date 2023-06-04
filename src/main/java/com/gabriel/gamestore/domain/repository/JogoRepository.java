package com.gabriel.gamestore.domain.repository;

import com.gabriel.gamestore.domain.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {

    Optional<Jogo> findByUriNome(String uriNome);

}
