package com.stagIA.tcc.repositories;

import com.stagIA.tcc.model.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudanteRepository extends JpaRepository<Estudante, Long> {



    public Optional<Estudante> findByEmail(String email);
}
