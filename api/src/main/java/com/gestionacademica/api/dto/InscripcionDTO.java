package com.gestionacademica.api.dto;

public record InscripcionDTO(
    Integer id,
    String carnetEstudiante,
    String nombreEstudiante,
    String codigoCurso,
    String nombreCurso,
    Double notaFinal,
    String fechaEvaluacion
) {}
