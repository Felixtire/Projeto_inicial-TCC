package com.stagIA.tcc.dto.entrada;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEntradaEstudante(
        @NotBlank
        String nome,
        @NotBlank
        String curso,
        @NotBlank
        String cpf,
        @NotBlank
        String telefone,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha) {
}
