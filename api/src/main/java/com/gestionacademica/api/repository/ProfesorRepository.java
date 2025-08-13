package com.gestionacademica.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionacademica.api.entity.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {


java.util.List<Profesor> findByNombreCompletoContainingIgnoreCaseOrCorreoContainingIgnoreCase(String nombre, String correo);
}
