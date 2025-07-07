package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import lombok.Data;

/*------------------------------------------*/

@Data
public class CursoDTO {

    // ID CURSO: Identificador del curso
    private int idCurso;                   

    // TITULO CURSO: Titulo del curso
    private String tituloCurso;           

    // DESCRIPCION CURSO: Descripción del curso
    private String descripcionCurso;       
    
}
