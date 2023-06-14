package com.gabriel.gamestore.api.controller;

import com.gabriel.gamestore.api.assembler.ModoAssembler;
import com.gabriel.gamestore.api.model.ModoModel;
import com.gabriel.gamestore.api.model.request.ModoRequest;
import com.gabriel.gamestore.api.security.roleauthotization.CheckSecurity;
import com.gabriel.gamestore.domain.service.ModoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/modos")
public class ModoController {

    private ModoService service;
    private ModoAssembler assembler;


    @GetMapping
    @CheckSecurity.Geral.podeConsultar
    public List<ModoModel> listar() {
        return assembler.toCollectionModel(service.listar());
    }

    @GetMapping("/{modoId}")
    @CheckSecurity.Geral.podeConsultar
    public ModoModel buscarPorId(@PathVariable Long modoId) {
        var modo = service.buscarPorId(modoId);
        return assembler.toModel(modo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CheckSecurity.Geral.podeGerenciar
    public ModoModel adicionar(@RequestBody ModoRequest request) {
        var modo = assembler.toEntity(request);
        return assembler.toModel(service.salvar(modo));
    }

    @PutMapping("/{modoId}")
    @CheckSecurity.Geral.podeGerenciar
    public ModoModel editar(@PathVariable Long modoId, @RequestBody ModoRequest request) {
        var modo = service.buscarPorId(modoId);
        assembler.copyToEntity(request, modo);
        return assembler.toModel(service.salvar(modo));
    }

    @DeleteMapping("/{modoId}")
    @CheckSecurity.Geral.podeGerenciar
    public void remover(@PathVariable Long modoId) {
        service.remover(modoId);
    }

}
