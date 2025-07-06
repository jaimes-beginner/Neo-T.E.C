package com.duoc.api_inscripciones.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class ProgresoCreate {
    
    // ID USUARIO PROGRESO: Id del usuario que se le quiere saber el progreso
    @NotNull
    private int idUsuarioProgreso;         

    // ID CURSO PROGRESO: Id del curso que se le quiere saber el progreso
    @NotNull
    private int idCursoProgreso;           

    // PORCENTAJE PROGRESO: Porcentaje del progreso
    @NotNull
    private int porcentajeProgreso;     

    // ULTIMA ACTIVIDAD PROGRESO: Ultima actividad en este contexto
    private String ultimaActividadProgreso;        


}
