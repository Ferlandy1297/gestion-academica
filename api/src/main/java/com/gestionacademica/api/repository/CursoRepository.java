// CursoRepository.java
package com.gestionacademica.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionacademica.api.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, String> {
}
