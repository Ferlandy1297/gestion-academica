package com.gestionacademica.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gestionacademica.api.dto.ReporteCursosPorProfesorDTO;
import com.gestionacademica.api.dto.ReporteInscripcionesPorCicloDTO;
import com.gestionacademica.api.dto.ReportePromedioPorCursoDTO;
import com.gestionacademica.api.dto.TopCursosPorPromedioDTO;
import com.gestionacademica.api.repository.ReporteRepository;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public List<ReporteCursosPorProfesorDTO> cursosPorProfesor() {
        return reporteRepository.cursosPorProfesor();
    }

    public List<ReportePromedioPorCursoDTO> promedioPorCurso() {
        return reporteRepository.promedioPorCurso();
    }

    public List<ReporteInscripcionesPorCicloDTO> inscripcionesPorCiclo() {
        return reporteRepository.inscripcionesPorCiclo();
    }

    public List<TopCursosPorPromedioDTO> topCursosPorPromedio(int limit) {
        if (limit <= 0) limit = 3;
        return reporteRepository.topCursosPorPromedio(PageRequest.of(0, limit));
    }
}
