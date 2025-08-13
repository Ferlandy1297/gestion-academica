package com.gestionacademica.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionacademica.api.entity.Curso;
import com.gestionacademica.api.repository.CursoRepository;
//import com.gestionacademica.api.repository.ProfesorRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    // @Autowired
    // private ProfesorRepository profesorRepository;

    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> obtenerPorCodigo(String codigo) {
        return cursoRepository.findById(codigo);
    }

    public Curso actualizarCurso(String codigo, Curso cursoActualizado) {
        return cursoRepository.findById(codigo).map(curso -> {
            curso.setNombre(cursoActualizado.getNombre());
            curso.setCreditos(cursoActualizado.getCreditos());
            curso.setSemestre(cursoActualizado.getSemestre());
            curso.setCiclo(cursoActualizado.getCiclo());
            curso.setProfesor(cursoActualizado.getProfesor());
            curso.setPrerequisito(cursoActualizado.getPrerequisito());
            return cursoRepository.save(curso);
        }).orElseThrow(() -> new RuntimeException("Curso no encontrado con código: " + codigo));
    }

    public void eliminarCurso(String codigo) {
        cursoRepository.deleteById(codigo);
    }

// ====== DTO conversion and filtered listing for Tarea 5 ======
@org.springframework.stereotype.Service
public static class _CursoDTOHelper {}

public com.gestionacademica.api.dto.CursoDTO toDTO(com.gestionacademica.api.entity.Curso c) {
    if (c == null) return null;
    Integer profId = (c.getProfesor() != null ? c.getProfesor().getId() : null);
    String profNom = (c.getProfesor() != null ? c.getProfesor().getNombreCompleto() : null);
    String preCod = (c.getPrerequisito() != null ? c.getPrerequisito().getCodigo() : null);
    return new com.gestionacademica.api.dto.CursoDTO(
        c.getCodigo(),
        c.getNombre(),
        c.getCreditos(),
        c.getSemestre(),
        c.getCiclo(),
        profId,
        profNom,
        preCod
    );
}

public java.util.List<com.gestionacademica.api.dto.CursoDTO> listarDTO(String ciclo, String semestre, Integer profesorId, String prerequisito) {
    java.util.List<com.gestionacademica.api.entity.Curso> base;
    if (ciclo != null && !ciclo.isBlank()) {
        base = cursoRepository.findByCicloIgnoreCase(ciclo);
    } else if (semestre != null && !semestre.isBlank()) {
        base = cursoRepository.findBySemestre(semestre);
    } else if (profesorId != null) {
        base = cursoRepository.findByProfesor_Id(profesorId);
    } else if (prerequisito != null && !prerequisito.isBlank()) {
        base = cursoRepository.findByPrerequisito_Codigo(prerequisito);
    } else {
        base = cursoRepository.findAll();
    }
    return base.stream().map(this::toDTO).toList();
}

public java.util.Optional<com.gestionacademica.api.dto.CursoDTO> obtenerDTO(String codigo) {
    return cursoRepository.findById(codigo).map(this::toDTO);
}
}
