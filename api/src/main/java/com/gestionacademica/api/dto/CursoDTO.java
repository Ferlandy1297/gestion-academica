package com.gestionacademica.api.dto;

public record CursoDTO(
    String codigo,
    String nombre,
    Integer creditos,
    String semestre,
    String ciclo,
    Integer profesorId,
    String profesorNombre,
    String prerequisitoCodigo
) {}
