package com.duoc.api_inscripciones.model.request;

import java.util.Date;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class ProgresoUpdate {
    
    // ID PROGRESO: El id del progreso para buscarlo
    @NotNull
    private int idProgreso;        
    
    // ID USUARIO PROGRESO: Id del usuario que se le quiere saber el progreso
    private int idUsuarioProgreso;         

    // ID CURSO PROGRESO: Id del curso que se le quiere saber el progreso
    private int idCursoProgreso;  

    // PORCENTAJE PROGRESO: Porcentaje del progreso
    private Double porcentajeProgreso;   

}
