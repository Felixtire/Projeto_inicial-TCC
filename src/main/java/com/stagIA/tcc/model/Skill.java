package com.stagIA.tcc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skill")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "skill_Id")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long skill_Id;

    @Column(nullable = false, length = 150)
    private String nome;

    private String descricao;
}
