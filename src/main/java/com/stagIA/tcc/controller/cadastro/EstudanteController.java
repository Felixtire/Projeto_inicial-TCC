package com.stagIA.tcc.controller.cadastro;

import com.stagIA.tcc.dto.entrada.DadosCadastroEntradaEstudante;
import com.stagIA.tcc.service.EstudanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cadastro-estudante")
public class EstudanteController {


    @Autowired
    private EstudanteService service;

    @PostMapping
    public ResponseEntity cadastrarEstudante(@RequestBody DadosCadastroEntradaEstudante dadosCadastroEntradaEstudante, UriComponentsBuilder uriComponentsBuilder){

        var estudante = service.cadastrar(dadosCadastroEntradaEstudante);

        var url = uriComponentsBuilder.path("/cadastro-estudante/{id}").buildAndExpand(estudante.estudante_id()).toUri();

        return ResponseEntity.created(url).body(estudante);


    }

    @GetMapping
    public ResponseEntity listarEstudantes(){

        var estudantes = service.listarEstudantes();

        return ResponseEntity.ok().body(estudantes);


    }

    @PatchMapping
    public ResponseEntity atualizarCadastro(@RequestBody DadosCadastroEntradaEstudante dados){

        var estudanteAtualizado = service.atualizarCadastro(dados);

        return ResponseEntity.ok().body(estudanteAtualizado);

    }

    @DeleteMapping
    public ResponseEntity deletarCadastro(@RequestParam Long id){

        service.deletarCadastro(id);

        return ResponseEntity.noContent().build();

    }


}
