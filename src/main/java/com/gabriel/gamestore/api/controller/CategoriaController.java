package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.CategoriaAssembler;
import com.gabriel.gamestore.api.model.CategoriaModel;
import com.gabriel.gamestore.api.model.request.CategoriaRequest;
import com.gabriel.gamestore.domain.model.Categoria;
import com.gabriel.gamestore.domain.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaService service;
    private CategoriaAssembler assembler;

    @GetMapping
    public List<CategoriaModel> listar() {
        return assembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{categoriaId}")
    public CategoriaModel buscarPorId(@PathVariable Long categoriaId) {
        return assembler.toModel(service.buscarPorId(categoriaId));
    }

    @PostMapping
    public CategoriaModel adicionar(@RequestBody CategoriaRequest request) {
        var categoria = assembler.toEntity(request);
        return assembler.toModel(service.salvar(categoria));
    }

    @PutMapping("/{categoriaId}")
    public CategoriaModel editar(@PathVariable Long categoriaId, @RequestBody CategoriaRequest request) {
        var categoria = service.buscarPorId(categoriaId);
        assembler.copyToEntity(request, categoria);
        return assembler.toModel(service.salvar(categoria));
    }

    @DeleteMapping("/{categoriaId}")
    public void remover(@PathVariable Long categoriaId) {
        service.remover(categoriaId);
    }
}
