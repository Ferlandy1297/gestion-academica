package com.gestionacademica.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestionacademica.api.dto.ReporteCursosPorProfesorDTO;
import com.gestionacademica.api.dto.ReporteInscripcionesPorCicloDTO;
import com.gestionacademica.api.dto.ReportePromedioPorCursoDTO;
import com.gestionacademica.api.dto.TopCursosPorPromedioDTO;
import com.gestionacademica.api.service.ReporteService;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    // Reporte 1
    @GetMapping("/cursos-por-profesor")
    public List<ReporteCursosPorProfesorDTO> cursosPorProfesor() {
        return reporteService.cursosPorProfesor();
    }

    // Reporte 2
    @GetMapping("/promedio-por-curso")
    public List<ReportePromedioPorCursoDTO> promedioPorCurso() {
        return reporteService.promedioPorCurso();
    }

    // Reporte 3
    @GetMapping("/inscripciones-por-ciclo")
    public List<ReporteInscripcionesPorCicloDTO> inscripcionesPorCiclo() {
        return reporteService.inscripcionesPorCiclo();
    }

    // Reporte 4 - Top N cursos por promedio (default 3)
    @GetMapping("/top-cursos-promedio")
    public List<TopCursosPorPromedioDTO> topCursosPorPromedio(@RequestParam(name = "limit", defaultValue = "3") int limit) {
        return reporteService.topCursosPorPromedio(limit);
    }
}
