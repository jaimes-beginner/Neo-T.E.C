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
    
    @Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    private int idEvaluacion;               // Identificador de la evaluación

    @Column(nullable = false)
    private int idCursoEvaluacion;          // Id del curso a la que está asociada la evaluacion

    @Column(nullable = false)
    private String tipoEvaluacion;          // Tipo de evaluación (quiz, final)


    /* Relacion con preguntas, en este caso una 
    evaluación puede tener varias preguntas */
    @JsonManagedReference
    @OneToMany(mappedBy = "evaluacionRelacion", 
        cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pregunta> listaPreguntas = new ArrayList<>();        // Preguntas que tiene la evaluación


    /* Relacion que tiene con contenido, en este caso 
    puede haber muchas evaluaciones en un contenido */
    @ManyToOne
    @JsonBackReference
    @JoinColumn
    (name = "idContenido", nullable = false)
    private Contenido contenido;        // Contenido por el que está relacionado Contenido


    private Date creacionEvaluacion;        // Fecha de creación de la evaluación

}
