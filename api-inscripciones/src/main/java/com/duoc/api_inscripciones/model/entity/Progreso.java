package com.duoc.api_inscripciones.model.entity;

import java.util.Date;

/*------------------------------------------*/

// Importaciones
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
@Table(name = "progress")
public class Progreso {
    
    @Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    private int idProgreso;                     // Identificador del progreso

    @Column(nullable = false)
    private int idUsuarioProgreso;              // Id del usuario al que se le quiere saber el progreso

    @Column(nullable = false)
    private int idCursoProgreso;                // Id del curso al que se le quiere saber el progreso

    @Column(nullable = false)
    private Double porcentajeProgreso;             // Porcentaje que se tiene del progreso

    @Column(nullable = true)
    private Date ultimaActividadProgreso;     // Registro de la última actividad

}
