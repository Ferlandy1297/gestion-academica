package com.gestionacademica.api.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionacademica.api.entity.Estudiante;
import com.gestionacademica.api.service.EstudianteService;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "*")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @PostMapping
    public Estudiante crear(@RequestBody Estudiante estudiante) {
        return estudianteService.crearEstudiante(estudiante);
    }

    @GetMapping
    public List<Estudiante> obtenerTodos() {
        return estudianteService.obtenerTodos();
    }

    @GetMapping("/{carnet}")
    public Optional<Estudiante> obtenerPorCarnet(@PathVariable String carnet) {
        return estudianteService.obtenerPorCarnet(carnet);
    }

    @PutMapping("/{carnet}")
    public Estudiante actualizar(@PathVariable String carnet, @RequestBody Estudiante estudiante) {
        return estudianteService.actualizarEstudiante(carnet, estudiante);
    }

    @DeleteMapping("/{carnet}")
    public void eliminar(@PathVariable String carnet) {
        estudianteService.eliminarEstudiante(carnet);
    }
}
