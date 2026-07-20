package com.stagIA.tcc.service;

import com.stagIA.tcc.dto.entrada.DadosCadastroEntradaEstudante;
import com.stagIA.tcc.dto.saida.DadosEstudanteCadastradoSaida;
import com.stagIA.tcc.dto.saida.DadosEstudantesListados;
import com.stagIA.tcc.model.Estudante;
import com.stagIA.tcc.model.enums.StatusEstudante;
import com.stagIA.tcc.repositories.EstudanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository repository;
    @Autowired
    private EnconderService enconderService;

    @Transactional
    public DadosEstudanteCadastradoSaida cadastrar(DadosCadastroEntradaEstudante dadosCadastroEntradaEstudante) {

        var estudante = new Estudante(dadosCadastroEntradaEstudante);

        estudante.setStatusEstudante(StatusEstudante.ATIVO);

        estudante.setSenha(enconderService.encoder(estudante.getSenha()));

        repository.save(estudante);

        return new DadosEstudanteCadastradoSaida(estudante);
    }

    public List<DadosEstudantesListados> listarEstudantes() {

        return repository.findAll()
                .stream()
                .map(DadosEstudantesListados :: new)
                .toList();
    }

    public DadosEstudanteCadastradoSaida atualizarCadastro(DadosCadastroEntradaEstudante dados) {

        var estudante = repository.findByEmail(dados.email())
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));

        if (dados.nome() != null) {
            estudante.setNome(dados.nome());
        }

        if (dados.email() != null && !dados.email().equals(estudante.getEmail())) {
            repository.findAll()
                    .stream()
                    .filter(e -> e.getEmail().equals(dados.email()))
                    .findFirst()
                    .ifPresent(e -> {
                        throw new RuntimeException("Email já está em uso");
                    });
            estudante.setEmail(dados.email());
        }

        if (dados.cpf() != null && !dados.cpf().equals(estudante.getCpf())) {
            repository.findAll()
                    .stream()
                    .filter(e -> e.getCpf().equals(dados.cpf()))
                    .findFirst()
                    .ifPresent(e -> {
                        throw new RuntimeException("CPF já está em uso");
                    });
            estudante.setCpf(dados.cpf());
        }

        if (dados.curso() != null) {
            estudante.setCurso(dados.curso());
        }

        return new DadosEstudanteCadastradoSaida(estudante);



    }

    @Transactional
    public void deletarCadastro(Long id) {

        var estudante = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));

        repository.delete(estudante);
    }
}
