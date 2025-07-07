package com.duoc.api_contenidos.model.entity;

/*------------------------------------------*/

// Importaciones
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/*------------------------------------------*/

@Data
@Entity
@Table(name = "evaluations")
public class Evaluacion {
    
    // ID EVALUACION: Identificador de la evaluación
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvaluacion;               

    // TIPO EVALUACION: Tipo de evaluación (quiz, final)
    @Column(nullable = false)
    private String tipoEvaluacion;   

    // CREACION EVALUACION: Fecha de creación de la evaluación
    private Date creacionEvaluacion;        

    // CONTENIDO: Puede haber muchas evaluaciones en un contenido
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idContenido", nullable = false)
    private Contenido contenido;    

    // LISTA PREGUNTAS: Una evaluacion puede tener muchas preguntas
    @JsonManagedReference
    @OneToMany(mappedBy = "evaluacionRelacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pregunta> listaPreguntas = new ArrayList<>();       

}
