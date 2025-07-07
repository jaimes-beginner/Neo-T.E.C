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
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

/*------------------------------------------*/

@Data
@Entity
@Table(name = "questions")    
public class Pregunta {
    
    // ID PREGUNTA: Identificador de la pregunta
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)          
    private int idPregunta;               

    // ENUNCIADO PREGUNTA: Enunciado de la pregunta
    @Column(nullable = false)
    private String enunciadoPregunta;    

    // OPCIONES PREGUNTA: Opciones-alternativas de la pregunta
    @Column(nullable = false)
    private String opcionesPregunta;       

    // OPCIONES CORRECTA PREGUNTA: opcion-alternativa correcta
    @Column(nullable = false)
    private String opcionesCorrectaPregunta;    
    
    // EVALUACION RELACION: Puede haber muchas pregunta en una evaluacion
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idEvaluacion", nullable = false)
    private Evaluacion evaluacionRelacion;  
    
}
