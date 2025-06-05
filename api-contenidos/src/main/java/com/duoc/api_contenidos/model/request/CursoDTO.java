package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import java.util.Date;
import lombok.Data;

/*------------------------------------------*/

@Data
public class CursoDTO {

    private int idCurso;                    // Identificador del curso

    private String tituloCurso;             // Titulo del curso

    private String descripcionCurso;        // Descripción del curso

    private String categoriaCurso;          // Categoria del curso

    private Boolean estadoCurso;            // Estado del curso

    private Date creacionCurso;             // Fecha de creación del curso

    private int idIntructorCurso;           // Instructor del curso
    
}
