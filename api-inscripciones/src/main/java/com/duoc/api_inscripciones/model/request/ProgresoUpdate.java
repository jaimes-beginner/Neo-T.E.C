package com.duoc.api_inscripciones.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class ProgresoUpdate {
    
    @NotNull
    private int idProgreso;         // El id del progreso para buscarlo
    
    private int idUsuarioProgreso;          // Id del usuario que se le quiere saber el progreso

    private int idCursoProgreso;            // Id del curso que se le quiere saber el progreso

    private int porcentajeProgreso;         // Porcentaje del progreso

    private String ultimaActividadProgreso;         // Ultima actividad en este contexto

}
