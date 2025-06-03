package com.duoc.api_cursos.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class CursoUpdate {

    @NotNull
    private int idCurso;        // Identificador del curos para buscarlo

    private String tituloCurso;             // Titulo de curso

    private String descripcionCurso;        // Descripcion del curso

    private String categoriaCurso;          // Categoria del curso

    private Integer idInstructorCurso;          // Instructor a cargo del curso
    
}
