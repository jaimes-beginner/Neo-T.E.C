package com.duoc.api_resenas.models.entities;

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
@Table(name = "resenia")            // Nombre de la tabla
public class Resenia {
    
    @Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    private int idResenia;                  // Id de la reseña

    @Column(nullable = false)
    private String textoResenia;            // Cuerpo de la reseña

    @Column(nullable = false)
    private int puntuacionResenia;          // Nota de la reseña

    private Date fechaResenia;              // Fecha de la reseña
}
