package com.gestionacademica.api.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Curso {

    @Id
    private String codigo;
    private String nombre;
    private Integer creditos;
    private String semestre;
    private String ciclo;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "prerequisito")
    private Curso prerequisito;

    @OneToMany(mappedBy = "curso")
    private List<Inscripcion> inscripciones;

    // Getters y Setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Curso getPrerequisito() {
        return prerequisito;
    }

    public void setPrerequisito(Curso prerequisito) {
        this.prerequisito = prerequisito;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }
}


  
