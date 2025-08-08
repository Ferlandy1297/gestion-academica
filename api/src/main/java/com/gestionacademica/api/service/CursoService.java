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
}

