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
@Table(name = "inscriptions")      
public class Inscripcion {
    
    // ID INSCRIPCION: Identificador del la inscripción
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInscripcion;                  

    // ID USUARIO INSCRIPCION: Identificador del usuario que se quiere inscribir
    @Column(nullable = false)
    private int idUsuarioInscripcion;           

    // ID CURSO INSCRIPCION: Identificador del curso al que se va a inscribir
    @Column(nullable = false)
    private int idCursoInscripcion;         

    // FECHA INSCRIPCION: Fecha de la inscripción
    private Date fechaInscripcion;           

    // ESTADO INSCRIPCION: El estado de la inscripción
    private Boolean estadoInscripcion;        

}
