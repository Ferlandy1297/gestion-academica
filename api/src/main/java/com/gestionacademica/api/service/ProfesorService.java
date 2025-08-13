package com.gestionacademica.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionacademica.api.entity.Profesor;
import com.gestionacademica.api.repository.ProfesorRepository;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    public Profesor crearProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public List<Profesor> obtenerTodos() {
        return profesorRepository.findAll();
    }

    public Optional<Profesor> obtenerPorId(Integer id) {
        return profesorRepository.findById(id);
    }

    public Profesor actualizarProfesor(Integer id, Profesor profesorActualizado) {
        return profesorRepository.findById(id).map(profesor -> {
            profesor.setNombreCompleto(profesorActualizado.getNombreCompleto());
            profesor.setCorreo(profesorActualizado.getCorreo());
            return profesorRepository.save(profesor);
        }).orElseThrow(() -> new RuntimeException("Profesor no encontrado con ID: " + id));
    }

    public void eliminarProfesor(Integer id) {
        profesorRepository.deleteById(id);
    }

// ====== DTO conversion and filtered listing for Tarea 5 ======
@org.springframework.stereotype.Service
public static class _ProfesorDTOHelper {}

public com.gestionacademica.api.dto.ProfesorDTO toDTO(com.gestionacademica.api.entity.Profesor p) {
    if (p == null) return null;
    return new com.gestionacademica.api.dto.ProfesorDTO(p.getId(), p.getNombreCompleto(), p.getCorreo());
}

public java.util.List<com.gestionacademica.api.dto.ProfesorDTO> listarDTO(String q) {
    java.util.List<com.gestionacademica.api.entity.Profesor> base;
    if (q != null && !q.isBlank()) {
        base = profesorRepository.findByNombreCompletoContainingIgnoreCaseOrCorreoContainingIgnoreCase(q, q);
    } else {
        base = profesorRepository.findAll();
    }
    return base.stream().map(this::toDTO).toList();
}

public java.util.Optional<com.gestionacademica.api.dto.ProfesorDTO> obtenerDTO(Integer id) {
    return profesorRepository.findById(id).map(this::toDTO);
}
}
