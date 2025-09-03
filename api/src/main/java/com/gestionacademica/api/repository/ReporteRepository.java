package com.gestionacademica.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestionacademica.api.dto.ReporteCursosPorProfesorDTO;
import com.gestionacademica.api.dto.ReporteInscripcionesPorCicloDTO;
import com.gestionacademica.api.dto.ReportePromedioPorCursoDTO;
import com.gestionacademica.api.dto.TopCursosPorPromedioDTO;
import com.gestionacademica.api.entity.Inscripcion;

@Repository
public interface ReporteRepository extends JpaRepository<Inscripcion, Integer> {

    // Reporte 1: número total de cursos que imparte cada profesor
    @Query("SELECT new com.gestionacademica.api.dto.ReporteCursosPorProfesorDTO(" +
           " p.nombreCompleto, COUNT(c) ) " +
           " FROM Curso c JOIN c.profesor p " +
           " GROUP BY p.nombreCompleto " +
           " ORDER BY COUNT(c) DESC")
    List<ReporteCursosPorProfesorDTO> cursosPorProfesor();

    // Reporte 2: nota promedio para cada curso
    @Query("SELECT new com.gestionacademica.api.dto.ReportePromedioPorCursoDTO(" +
           " c.nombre, AVG(i.notaFinal) ) " +
           " FROM Inscripcion i JOIN i.curso c " +
           " GROUP BY c.nombre " +
           " ORDER BY AVG(i.notaFinal) DESC")
    List<ReportePromedioPorCursoDTO> promedioPorCurso();

    // Reporte 3: estudiantes inscritos por ciclo (estudiantes únicos por ciclo)
    @Query("SELECT new com.gestionacademica.api.dto.ReporteInscripcionesPorCicloDTO(" +
           " i.curso.ciclo, COUNT(DISTINCT i.estudiante.carnet) ) " +
           " FROM Inscripcion i " +
           " GROUP BY i.curso.ciclo " +
           " ORDER BY i.curso.ciclo ASC")
    List<ReporteInscripcionesPorCicloDTO> inscripcionesPorCiclo();

    // Reporte 4: Top cursos con mayor promedio (limit por Pageable)
    @Query("SELECT new com.gestionacademica.api.dto.TopCursosPorPromedioDTO(" +
           " c.nombre, AVG(i.notaFinal) ) " +
           " FROM Inscripcion i JOIN i.curso c " +
           " GROUP BY c.nombre " +
           " ORDER BY AVG(i.notaFinal) DESC")
    List<TopCursosPorPromedioDTO> topCursosPorPromedio(Pageable pageable);
}
