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
 
// ====== DTO conversion and filtered listing for Tarea 5 ======
@org.springframework.stereotype.Service
public static class _InscripcionDTOHelper {}

private static String fmtFecha(java.util.Date d) {
    if (d == null) return null;
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(d);
}

public com.gestionacademica.api.dto.InscripcionDTO toDTO(com.gestionacademica.api.entity.Inscripcion i) {
    if (i == null) return null;
    String carnet = (i.getEstudiante() != null ? i.getEstudiante().getCarnet() : null);
    String nombreEst = (i.getEstudiante() != null ? (i.getEstudiante().getNombre() + " " + i.getEstudiante().getApellido()) : null);
    String codCurso = (i.getCurso() != null ? i.getCurso().getCodigo() : null);
    String nomCurso = (i.getCurso() != null ? i.getCurso().getNombre() : null);
    return new com.gestionacademica.api.dto.InscripcionDTO(
        i.getId(),
        carnet,
        nombreEst != null ? nombreEst.trim() : null,
        codCurso,
        nomCurso,
        i.getNotaFinal(),
        fmtFecha(i.getFechaEvaluacion())
    );
}

public java.util.List<com.gestionacademica.api.dto.InscripcionDTO> listarDTO(String carnet, String codigoCurso, Double notaMin, Double notaMax, java.util.Date desde, java.util.Date hasta) {
    java.util.List<com.gestionacademica.api.entity.Inscripcion> base;
    if (carnet != null && !carnet.isBlank()) {
        base = inscripcionRepository.findByEstudiante_Carnet(carnet);
    } else if (codigoCurso != null && !codigoCurso.isBlank()) {
        base = inscripcionRepository.findByCurso_Codigo(codigoCurso);
    } else if (notaMin != null && notaMax != null) {
        base = inscripcionRepository.findByNotaFinalBetween(notaMin, notaMax);
    } else if (desde != null && hasta != null) {
        base = inscripcionRepository.findByFechaEvaluacionBetween(desde, hasta);
    } else {
        base = inscripcionRepository.findAll();
    }
    return base.stream().map(this::toDTO).toList();
}

public java.util.Optional<com.gestionacademica.api.dto.InscripcionDTO> obtenerDTO(Integer id) {
    return inscripcionRepository.findById(id).map(this::toDTO);
}
}
