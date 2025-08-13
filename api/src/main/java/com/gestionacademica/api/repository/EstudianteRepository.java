package com.gestionacademica.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionacademica.api.entity.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, String> {


java.util.List<Estudiante> findByApellidoContainingIgnoreCase(String apellido);
    java.util.List<Estudiante> findByNombreContainingIgnoreCase(String nombre);
}
