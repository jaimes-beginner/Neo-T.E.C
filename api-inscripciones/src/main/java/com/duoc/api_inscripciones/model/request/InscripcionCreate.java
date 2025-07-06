package com.duoc.api_inscripciones.model.request;

import jakarta.validation.constraints.NotNull;

/*------------------------------------------*/

// Importaciones
import lombok.Data;

/*------------------------------------------*/

@Data
public class InscripcionCreate {
    
    // ID USUARIO: Identificador del usuario que se quiere inscribir
    @NotNull
    private Integer idUsuario;         

    // ID CURSO: Identificador del curso al que se va a inscribir
    @NotNull
    private Integer idCurso;            

    @NotNull 
    private Double montoInscripcion;

}
