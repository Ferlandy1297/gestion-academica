package com.gestionacademica.api.service;

 import java.util.List;
 import java.util.Optional;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import com.gestionacademica.api.entity.Inscripcion;
 import com.gestionacademica.api.repository.InscripcionRepository;

 import lombok.Data;

 @Service
 @Data

 public class InscripcionService {

     @Autowired
     private InscripcionRepository inscripcionRepository;

     public Inscripcion crearInscripcion(Inscripcion inscripcion) {
         return inscripcionRepository.save(inscripcion);
     }

     public List<Inscripcion> obtenerTodas() {
         return inscripcionRepository.findAll();
     }

     public Optional<Inscripcion> obtenerPorId(Integer id) {
         return inscripcionRepository.findById(id);
     }

     public Inscripcion actualizarInscripcion(Integer id, Inscripcion inscripcionActualizada) {
         return inscripcionRepository.findById(id).map(inscripcion -> {
             inscripcion.setEstudiante(inscripcionActualizada.getEstudiante());
             inscripcion.setCurso(inscripcionActualizada.getCurso());
             inscripcion.setNotaFinal(inscripcionActualizada.getNotaFinal());
             return inscripcionRepository.save(inscripcion);
         }).orElseThrow(() -> new RuntimeException("Inscripción no encontrada con ID: " + id));
     }

     public void eliminarInscripcion(Integer id) {
         inscripcionRepository.deleteById(id);
     }
 }
