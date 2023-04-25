package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.CategoriaAssembler;
import com.gabriel.gamestore.api.model.CategoriaModel;
import com.gabriel.gamestore.api.model.request.CategoriaRequest;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaService service;
    private CategoriaAssembler assembler;

    @GetMapping
    @CheckSecurity.Geral.podeConsultar
    public List<CategoriaModel> listar() {
        return assembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{categoriaId}")
    @CheckSecurity.Geral.podeConsultar
    public CategoriaModel buscarPorId(@PathVariable Long categoriaId) {
        return assembler.toModel(service.buscarPorId(categoriaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Geral.podeGerenciar
    public CategoriaModel adicionar(@Valid @RequestBody CategoriaRequest request) {
        var categoria = assembler.toEntity(request);
        return assembler.toModel(service.salvar(categoria));
    }

    @PutMapping("/{categoriaId}")
    @CheckSecurity.Geral.podeGerenciar
    public CategoriaModel editar(@PathVariable Long categoriaId, @Valid @RequestBody CategoriaRequest request) {
        var categoria = service.buscarPorId(categoriaId);
        assembler.copyToEntity(request, categoria);
        return assembler.toModel(service.salvar(categoria));
    }

    @DeleteMapping("/{categoriaId}")
    @CheckSecurity.Geral.podeGerenciar
    public void remover(@PathVariable Long categoriaId) {
        service.remover(categoriaId);
    }
}
