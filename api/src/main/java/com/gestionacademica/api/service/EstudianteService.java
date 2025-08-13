package com.gestionacademica.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionacademica.api.entity.Estudiante;
import com.gestionacademica.api.repository.EstudianteRepository;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public Estudiante crearEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public List<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> obtenerPorCarnet(String carnet) {
        return estudianteRepository.findById(carnet);
    }

    public Estudiante actualizarEstudiante(String carnet, Estudiante estudianteActualizado) {
        return estudianteRepository.findById(carnet).map(estudiante -> {
            estudiante.setNombre(estudianteActualizado.getNombre());
            estudiante.setApellido(estudianteActualizado.getApellido());
            estudiante.setFechaNacimiento(estudianteActualizado.getFechaNacimiento());
            return estudianteRepository.save(estudiante);
        }).orElseThrow(() -> new RuntimeException("Estudiante no encontrado con carnet: " + carnet));
    }

    public void eliminarEstudiante(String carnet) {
        estudianteRepository.deleteById(carnet);
    }

// ====== DTO conversion and filtered listing for Tarea 5 ======
@org.springframework.stereotype.Service
public static class _EstudianteDTOHelper {}

private java.time.LocalDate toLocalDate(java.util.Date d) {
    if (d == null) return null;
    if (d instanceof java.sql.Date sql) {
        return sql.toLocalDate();
    }
    return d.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
}

public com.gestionacademica.api.dto.EstudianteDTO toDTO(com.gestionacademica.api.entity.Estudiante e) {
    if (e == null) return null;
    String nombreCompleto = (e.getNombre() != null ? e.getNombre() : "") +
        (e.getApellido() != null ? (" " + e.getApellido()) : "");
    Integer edad = null;
    java.time.LocalDate birth = toLocalDate(e.getFechaNacimiento());
    if (birth != null) {
        edad = java.time.Period.between(birth, java.time.LocalDate.now()).getYears();
    }
    return new com.gestionacademica.api.dto.EstudianteDTO(e.getCarnet(), nombreCompleto.trim(), edad);
}

public java.util.List<com.gestionacademica.api.dto.EstudianteDTO> listarDTO(String nombre, String apellido) {
    java.util.List<com.gestionacademica.api.entity.Estudiante> base;
    if (apellido != null && !apellido.isBlank()) {
        base = estudianteRepository.findByApellidoContainingIgnoreCase(apellido);
    } else if (nombre != null && !nombre.isBlank()) {
        base = estudianteRepository.findByNombreContainingIgnoreCase(nombre);
    } else {
        base = estudianteRepository.findAll();
    }
    return base.stream().map(this::toDTO).toList();
}

public java.util.Optional<com.gestionacademica.api.dto.EstudianteDTO> obtenerDTO(String carnet) {
    return estudianteRepository.findById(carnet).map(this::toDTO);
}
}
