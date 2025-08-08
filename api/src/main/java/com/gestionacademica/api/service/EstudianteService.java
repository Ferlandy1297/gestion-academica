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
}