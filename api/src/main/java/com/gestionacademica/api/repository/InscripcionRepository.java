package com.gestionacademica.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionacademica.api.entity.Inscripcion;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    

java.util.List<Inscripcion> findByEstudiante_Carnet(String carnet);
    java.util.List<Inscripcion> findByCurso_Codigo(String codigo);
    java.util.List<Inscripcion> findByNotaFinalBetween(Double min, Double max);
    java.util.List<Inscripcion> findByFechaEvaluacionBetween(java.util.Date desde, java.util.Date hasta);
}
