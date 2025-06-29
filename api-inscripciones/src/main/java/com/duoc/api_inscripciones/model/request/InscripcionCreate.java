package com.duoc.api_inscripciones.model.request;

/*------------------------------------------*/

// Importaciones
import lombok.Data;

/*------------------------------------------*/

@Data
public class InscripcionCreate {
    
    private int idUsuarioInscripcion;           // Identificador del usuario que se quiere inscribir

    private int idCursoInscripcion;             // Identificador del curso al que se va a inscribir

}
