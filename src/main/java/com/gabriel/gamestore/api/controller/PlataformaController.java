package com.gabriel.gamestore.api.controller;

import com.electronwill.nightconfig.core.conversion.Path;
import com.gabriel.gamestore.api.assembler.PlataformaAssembler;
import com.gabriel.gamestore.api.model.PlataformaModel;
import com.gabriel.gamestore.api.model.request.PlataformaRequest;
import com.gabriel.gamestore.domain.service.PlataformaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/plataformas")
public class PlataformaController {

    private PlataformaService service;
    private PlataformaAssembler assembler;

    @GetMapping
    public List<PlataformaModel> listar() {
        return assembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{plataformaId}")
    public PlataformaModel buscarPorId(@PathVariable Long plataformaId) {
        return assembler.toModel(service.buscarPorId(plataformaId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlataformaModel adicionar(@Valid @RequestBody PlataformaRequest request) {
        var plataforma = assembler.toEntity(request);
        return assembler.toModel(service.salvar(plataforma));
    }

    @PutMapping("/{plataformaId}")
    public PlataformaModel editar(@PathVariable Long plataformaId, @Valid @RequestBody PlataformaRequest request) {
        var plataforma = service.buscarPorId(plataformaId);
        assembler.copyToEntity(request, plataforma);
        return assembler.toModel(service.salvar(plataforma));
    }

    @DeleteMapping("/{plataformaId}")
    public void remover(@PathVariable Long plataformaId) {
        service.remover(plataformaId);
    }

}

