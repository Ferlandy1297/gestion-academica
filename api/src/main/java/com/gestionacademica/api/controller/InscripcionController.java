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

 import com.gestionacademica.api.entity.Inscripcion;
 import com.gestionacademica.api.service.InscripcionService;

 @RestController
 @RequestMapping("/api/inscripciones")
 @CrossOrigin(origins = "*")

 public class InscripcionController {

     @Autowired
     private InscripcionService inscripcionService;

     @PostMapping
     public Inscripcion crear(@RequestBody Inscripcion inscripcion) {
         return inscripcionService.crearInscripcion(inscripcion);
     }

     @GetMapping
     public List<Inscripcion> obtenerTodas() {
         return inscripcionService.obtenerTodas();
     }

     @GetMapping("/{id}")
     public Optional<Inscripcion> obtenerPorId(@PathVariable Integer id) {
         return inscripcionService.obtenerPorId(id);
     }

     @PutMapping("/{id}")
     public Inscripcion actualizar(@PathVariable Integer id, @RequestBody Inscripcion inscripcion) {
         return inscripcionService.actualizarInscripcion(id, inscripcion);
     }

     @DeleteMapping("/{id}")
     public void eliminar(@PathVariable Integer id) {
         inscripcionService.eliminarInscripcion(id);
     }
 }
