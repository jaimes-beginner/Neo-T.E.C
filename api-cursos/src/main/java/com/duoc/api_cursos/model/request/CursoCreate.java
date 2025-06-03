package com.duoc.api_cursos.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class CursoCreate {
    
    @NotBlank
    private String tituloCurso;             // Titulo de curso

    @NotBlank
    private String descripcionCurso;        // Descripcion del curso

    @NotBlank
    private String categoriaCurso;          // Categoria del curso

    @NotNull
    private int idInstructorCurso;          // Instructor a cargo del curso

}
