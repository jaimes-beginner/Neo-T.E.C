package com.duoc.api_contenidos.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    private int idEvaluacionPregunta;       // Id de la evaluación a la le pertenece esta la pegúnta

    @Column(nullable = false)
    private String enunciadoPregunta;       // Enunciado de la pregunta

    @Column(nullable = false)
    private String opcionesPregunta;        // Opciones-alternativas de la pregunta

    @Column(nullable = false)
    private String respCorrectaPregunta;    // La respuesta correcta

    
    /* Relacion que tiene con contenido, en este caso
    pueden haber muchas preguntas en una evaluación */
    @ManyToOne
    @JsonBackReference
    @JoinColumn
    (name = "idEvaluacion", nullable = false)
    private Evaluacion evaluacion;
}
