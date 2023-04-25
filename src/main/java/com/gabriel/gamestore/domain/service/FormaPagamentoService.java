package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.exception.FormaPagamentoNaoEncontradaException;
import com.gabriel.gamestore.domain.model.FormaPagamento;
import com.gabriel.gamestore.domain.repository.FormaPagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FormaPagamentoService {

    private FormaPagamentoRepository repository;

    @Transactional(readOnly = true)
    public List<FormaPagamento> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public FormaPagamento buscarPorId(Long formaPagamentoId) {
        return repository.findById(formaPagamentoId).orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return repository.save(formaPagamento);
    }

    public void remover(Long formaPagamentoId) {
        var formaPagamento = buscarPorId(formaPagamentoId);
        repository.delete(formaPagamento);
    }

}
