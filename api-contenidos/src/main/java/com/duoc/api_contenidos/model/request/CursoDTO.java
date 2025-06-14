package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import lombok.Data;

/*------------------------------------------*/

@Data
public class CursoDTO {

    private int idCurso;                    // Identificador del curso

    private String tituloCurso;             // Titulo del curso

    private String descripcionCurso;        // Descripción del curso
    
}
