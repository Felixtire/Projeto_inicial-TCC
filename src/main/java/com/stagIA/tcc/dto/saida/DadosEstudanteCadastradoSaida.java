package com.stagIA.tcc.dto.saida;

import com.stagIA.tcc.model.Estudante;

public record DadosEstudanteCadastradoSaida(Long estudante_id, String email, String curso) {

    public DadosEstudanteCadastradoSaida(Estudante estudante) {
     this(estudante.getEstudanteId(), estudante.getEmail(), estudante.getCurso());

    }


}
