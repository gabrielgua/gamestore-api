package com.gabriel.gamestore.domain.service;

import com.gabriel.gamestore.domain.exception.EntidadeEmUsoException;
import com.gabriel.gamestore.domain.exception.JogoNaoEncontradoException;
import com.gabriel.gamestore.domain.exception.NegocioException;
import com.gabriel.gamestore.domain.model.Categoria;
import com.gabriel.gamestore.domain.model.Jogo;
import com.gabriel.gamestore.domain.model.Plataforma;
import com.gabriel.gamestore.domain.repository.JogoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JogoService {

    private static final int NUM_LISTA_DESTAQUE = 9;
    private JogoRepository repository;

    private ModoService modoService;
    private CategoriaService categoriaService;
    private PlataformaService plataformaService;
    private DesenvolvedoraService desenvolvedoraService;



    @Transactional(readOnly = true)
    public Long count() {
        return repository.count();
    }

    @Transactional(readOnly = true)
    public Page<Jogo> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Jogo> listarDestaques() {
        var random = new Random();
        var jogos = repository.findAll();
        Set<Jogo> randomJogos = new HashSet<>();

        while (randomJogos.size() < NUM_LISTA_DESTAQUE) {
            var randomIndex = random.nextInt(jogos.size());
            randomJogos.add(jogos.get(randomIndex));
        }

        var randomJogosList = new ArrayList<>(randomJogos.stream().toList());
        Collections.shuffle(randomJogosList);
        return randomJogosList;
    }


    @Transactional(readOnly = true)
    public Jogo buscarPorId(Long jogoId) {
        return repository.findById(jogoId).orElseThrow(() -> new JogoNaoEncontradoException(jogoId));
    }
    @Transactional(readOnly = true)
    public Jogo buscarPorUriNome(String uriNome) {
        return repository.findByUriNome(uriNome).orElseThrow(() -> new JogoNaoEncontradoException(uriNome));
    }
    @Transactional(readOnly = true)
    public List<Jogo> buscarVariosPorId(List<Long> jogosIds) {
        return jogosIds.stream()
                .map(this::buscarPorId)
                .collect(Collectors.toList());
    }
    @Transactional
    public Jogo salvar(Jogo jogo, Set<Long> plataformaIds, Set<Long> categoriaIds, Set<Long> modoIds) {

        var desenvolvedora = desenvolvedoraService.buscarPorId(jogo.getDesenvolvedora().getId());
        var uriNome = transformarNomeToUriNome(jogo.getNome());
        verificarUriNomeJaCadastrado(uriNome, jogo.getId());

        adicionarModos(jogo, modoIds);
        adicionarPlataformas(jogo, plataformaIds);
        adicionarCategorias(jogo, categoriaIds);


        jogo.setUriNome(uriNome);
        jogo.setDesenvolvedora(desenvolvedora);

        return repository.save(jogo);
    }

    @Transactional
    public void remover(Long jogoId) {
        try {
            var jogo = buscarPorId(jogoId);
            repository.delete(jogo);
            repository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String.format("Jogo de id: #%s está em uso. Remova-o da conta dos usuários e tente novamente.", jogoId));
        }
    }

    @Transactional
    public void adicionarPlataformas(Jogo jogo, Set<Long> plataformaIds) {
        jogo.setPlataformas(new HashSet<>());
        var plataformas = plataformaService.buscarVariosPorId(plataformaIds);

        plataformas.forEach(jogo::addPlataforma);
    }

    @Transactional
    public void adicionarCategorias(Jogo jogo, Set<Long> categoriaIds) {
        jogo.setCategorias(new HashSet<>());
        var categorias = categoriaService.buscarVariosPorIds(categoriaIds);

        categorias.forEach(jogo::addCategoria);
    }

    @Transactional
    public void adicionarModos(Jogo jogo, Set<Long> modoIds) {
        jogo.setModos(new HashSet<>());
        var modos = modoService.buscarVariosPorId(modoIds);

        modos.forEach(jogo::addModo);
    }

    private String transformarNomeToUriNome(String nome) {
        String stringTransformada = nome.toLowerCase();
        stringTransformada = stringTransformada.replaceAll("[^a-z A-Z0-9]", "");
        stringTransformada = stringTransformada.replaceAll(" +", " ");
        stringTransformada = stringTransformada.replaceAll(" ", "-");
        return stringTransformada;
    }

    private void verificarUriNomeJaCadastrado(String uriNome, Long jogoId) {
        var jogo = repository.findByUriNome(uriNome);
        if (jogo.isPresent() && !Objects.equals(jogo.get().getId(), jogoId)) {
            throw new NegocioException(String.format("Jogo '%s' já está cadastrado.", jogo.get().getNome()));
        }
    }
}
