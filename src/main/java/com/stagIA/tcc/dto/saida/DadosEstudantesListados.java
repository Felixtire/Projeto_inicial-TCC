package com.stagIA.tcc.dto.saida;

import com.stagIA.tcc.model.Estudante;
import com.stagIA.tcc.model.enums.StatusEstudante;

public record DadosEstudantesListados(String nome, String email, String cpf, String curso, StatusEstudante status) {
    public DadosEstudantesListados(Estudante estudante) {
      this(estudante.getNome(), estudante.getEmail(), estudante.getCpf(), estudante.getCurso(), estudante.getStatusEstudante());

    }
}
