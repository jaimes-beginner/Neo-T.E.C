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
@Table(name = "resenia")     
public class Resenia {
    
    // ID RESENIA: Id de la reseña
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResenia;              

    // TEXTO RESENIA: Cuerpo de la reseña
    @Column(nullable = false)
    private String textoResenia;           

    // PUNTUACION RESENIA: Nota de la reseña
    @Column(nullable = false)
    private int puntuacionResenia;       

    // FECHA RESENIA: Fecha de la reseña
    private Date fechaResenia;            

    // ID USUARIO RESENIA: Id del usuario que hizo la reseña
    @Column(nullable = false)
    private int idUsuarioResenia;          

    // ID CURSO RESENIA: Id del en donde se hizo de la reseña
    @Column(nullable = false)
    private int idCursoResenia;             

}
