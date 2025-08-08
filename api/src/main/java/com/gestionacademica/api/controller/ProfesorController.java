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

import com.gestionacademica.api.entity.Profesor;
import com.gestionacademica.api.service.ProfesorService;

@RestController
@RequestMapping("/api/profesores")
@CrossOrigin(origins = "*")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @PostMapping
    public Profesor crear(@RequestBody Profesor profesor) {
        return profesorService.crearProfesor(profesor);
    }

    @GetMapping
    public List<Profesor> obtenerTodos() {
        return profesorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Profesor> obtenerPorId(@PathVariable Integer id) {
        return profesorService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Profesor actualizar(@PathVariable Integer id, @RequestBody Profesor profesor) {
        return profesorService.actualizarProfesor(id, profesor);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        profesorService.eliminarProfesor(id);
    }
}