package com.gabriel.gamestore.core.spec;

import com.gabriel.gamestore.domain.filter.JogoFilter;
import com.gabriel.gamestore.domain.model.Jogo;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class JogoSpecs {
    public static Specification<Jogo> filter(JogoFilter filter) {
        return (root, query, criteriaBuilder) -> {

            var predicates = new ArrayList<Predicate>();

            if (filter.getNome() != null && !filter.getNome().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("nome"), "%" + filter.getNome().replaceAll("\\s+", "%") + "%"));
            }

            if (filter.getCategoriasIds() != null && !filter.getCategoriasIds().isEmpty()) {
                predicates.add(root.get("categorias").get("id").in(filter.getCategoriasIds()));
            }

            if (filter.getPlataformasIds() != null && !filter.getPlataformasIds().isEmpty()) {
                predicates.add(root.get("plataformas").get("id").in(filter.getPlataformasIds()));
            }

            if (filter.getModosIds() != null && !filter.getModosIds().isEmpty()) {
                predicates.add(root.get("modos").get("id").in(filter.getModosIds()));
            }

            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
