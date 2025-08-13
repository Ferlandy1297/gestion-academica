// CursoRepository.java
package com.gestionacademica.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionacademica.api.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, String> {

java.util.List<Curso> findByCicloIgnoreCase(String ciclo);
    java.util.List<Curso> findBySemestre(String semestre);
    java.util.List<Curso> findByProfesor_Id(Integer id);
    java.util.List<Curso> findByPrerequisito_Codigo(String codigo);
}
