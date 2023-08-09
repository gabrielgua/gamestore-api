package com.gabriel.gamestore.domain.repository;

import com.gabriel.gamestore.domain.model.ChaveAtivacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChaveAtivacaoRepository extends JpaRepository<ChaveAtivacao, Long> {

}
