package com.duoc.api_inscripciones.model.entity;

/*------------------------------------------*/

// Importaciones
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*------------------------------------------*/

@Data
@Entity
@Table(name = "inscriptions")           // Nombre de la tabla
public class Inscripcion {
    
    @Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    private int idInscripcion;                  // Identificador del la inscripción

    @Column(nullable = false)
    private int idUsuarioInscripcion;           // Identificador del usuario que se quiere inscribir

    @Column(nullable = false)
    private int idCursoInscripcion;             // Identificador del curso al que se va a inscribir

    private Date fechaInscripcion;              // Fecha de la inscripción

    private Boolean estadoInscripcion;          // El estado de la inscripción

}
