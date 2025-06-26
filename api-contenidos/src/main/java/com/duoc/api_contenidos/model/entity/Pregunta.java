package com.duoc.api_contenidos.model.entity;

/*------------------------------------------*/

// Importaciones
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

/*------------------------------------------*/

@Data
@Entity
@Table(name = "questions")          // El nombre de la tabla
public class Pregunta {
    
    @Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)          
    private int idPregunta;                 // Identificador de la pregunta

    @Column(nullable = false)
    private String enunciadoPregunta;       // Enunciado de la pregunta

    @Column(nullable = false)
    private String opcionesPregunta;        // Opciones-alternativas de la pregunta

    @Column(nullable = false)
    private String opcionesCorrectaPregunta;    // opcion-alternativa correcta
    
    // Puede haber muchas pregunta en una evaluacion
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idEvaluacion", nullable = false)
    private Evaluacion evaluacionRelacion;  
    
}
