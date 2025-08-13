package com.gestionacademica.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public java.util.List<com.gestionacademica.api.dto.EstudianteDTO> listar(
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) String apellido
    ) {
        return estudianteService.listarDTO(nombre, apellido);
    }


    
    @GetMapping("/{carnet}")
    public java.util.Optional<com.gestionacademica.api.dto.EstudianteDTO> obtener(@PathVariable String carnet) {
        return estudianteService.obtenerDTO(carnet);
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
