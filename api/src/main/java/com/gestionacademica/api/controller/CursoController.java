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

import com.gestionacademica.api.entity.Curso;
import com.gestionacademica.api.service.CursoService;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public Curso crear(@RequestBody Curso curso) {
        return cursoService.crearCurso(curso);
    }

    
    @GetMapping
    public java.util.List<com.gestionacademica.api.dto.CursoDTO> listar(
        @RequestParam(required = false) String ciclo,
        @RequestParam(required = false) String semestre,
        @RequestParam(required = false) Integer profesorId,
        @RequestParam(required = false) String prerequisito
    ) {
        return cursoService.listarDTO(ciclo, semestre, profesorId, prerequisito);
    }


    
    @GetMapping("/{codigo}")
    public java.util.Optional<com.gestionacademica.api.dto.CursoDTO> obtener(@PathVariable String codigo) {
        return cursoService.obtenerDTO(codigo);
    }


    @PutMapping("/{codigo}")
    public Curso actualizar(@PathVariable String codigo, @RequestBody Curso curso) {
        return cursoService.actualizarCurso(codigo, curso);
    }

    @DeleteMapping("/{codigo}")
    public void eliminar(@PathVariable String codigo) {
        cursoService.eliminarCurso(codigo);
    }
}

