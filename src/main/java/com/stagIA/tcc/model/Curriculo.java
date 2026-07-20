package com.stagIA.tcc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "curriculo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "curriculo_id")
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long curriculo_id;

    @ManyToOne
    @JoinColumn(name = "estudante_id", nullable = false)
    private Estudante estudante;

    @Column(columnDefinition = "TEXT")
    private String experiencia_profissional;

    private String links;

    @Column(columnDefinition = "TEXT")
    private String campo_livre;


}
