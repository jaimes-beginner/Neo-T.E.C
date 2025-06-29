package com.duoc.api_inscripciones.model.request;

import jakarta.validation.constraints.NotNull;

/*------------------------------------------*/

// Importaciones
import lombok.Data;

/*------------------------------------------*/

@Data
public class InscripcionCreate {
    
    @NotNull
    private Integer idUsuario;           // Identificador del usuario que se quiere inscribir

    @NotNull
    private Integer idCurso;             // Identificador del curso al que se va a inscribir

}
