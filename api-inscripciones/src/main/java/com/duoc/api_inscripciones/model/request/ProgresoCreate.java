package com.duoc.api_inscripciones.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class ProgresoCreate {
    
    @NotNull
    private int idUsuarioProgreso;          // Id del usuario que se le quiere saber el progreso

    @NotNull
    private int idCursoProgreso;            // Id del curso que se le quiere saber el progreso

    @NotNull
    private int porcentajeProgreso;         // Porcentaje del progreso

    private String ultimaActividadProgreso;         // Ultima actividad en este contexto

}
