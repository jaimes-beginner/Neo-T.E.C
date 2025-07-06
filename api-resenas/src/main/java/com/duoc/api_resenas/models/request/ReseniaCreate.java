package com.duoc.api_resenas.models.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class ReseniaCreate {

    // TEXTO RESENIA: Cuerpo de la reseña
    @NotBlank
    private String textoResenia;           

    // PUNTUACION RESENIA: Nota de la Reseña
    @NotNull
    private int puntuacionResenia;      

    // ID USUARIO RESENIA: Id del usuario que hizo la reseña
    @NotNull
    private int idUsuarioResenia;          

    // ID CURSO RESENIA: Id del en donde se hizo de la reseña
    @NotNull
    private int idCursoResenia;            

}
