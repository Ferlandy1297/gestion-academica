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
    public java.util.List<com.gestionacademica.api.dto.InscripcionDTO> listar(
        @RequestParam(required = false) String carnet,
        @RequestParam(required = false) String codigoCurso,
        @RequestParam(required = false) Double notaMin,
        @RequestParam(required = false) Double notaMax,
        @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
        @RequestParam(required = false) java.util.Date desde,
        @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
        @RequestParam(required = false) java.util.Date hasta
    ) {
        return inscripcionService.listarDTO(carnet, codigoCurso, notaMin, notaMax, desde, hasta);
    }


     
    @GetMapping("/{id}")
    public java.util.Optional<com.gestionacademica.api.dto.InscripcionDTO> obtener(@PathVariable Integer id) {
        return inscripcionService.obtenerDTO(id);
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
