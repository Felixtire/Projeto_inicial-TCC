package com.stagIA.tcc.model;

import com.stagIA.tcc.dto.entrada.DadosCadastroEntradaEstudante;
import com.stagIA.tcc.model.enums.StatusEstudante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudante")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "estudanteId")
public class Estudante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estudante_id")
    private Long estudanteId;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = true, length = 150)
    private String curso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status_estudante")
    private StatusEstudante statusEstudante;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @OneToOne(mappedBy = "estudante", cascade = CascadeType.ALL, orphanRemoval = true)
    private Curriculo curriculo;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name= "estudante_skill",
            joinColumns = @JoinColumn(name = "estudante_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(mappedBy = "estudante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> matches = new ArrayList<>();

    public Estudante(DadosCadastroEntradaEstudante dadosCadastroEntradaEstudante) {
        this.cpf = dadosCadastroEntradaEstudante.cpf();
        this.nome = dadosCadastroEntradaEstudante.nome();
        this.curso = dadosCadastroEntradaEstudante.curso();
        this.email = dadosCadastroEntradaEstudante.email();
        this.senha = dadosCadastroEntradaEstudante.senha();

    }
}
