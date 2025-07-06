package com.duoc.api_cursos.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class CursoCreate {
    
    // TITULO CURSO: Titulo de curso
    @NotBlank
    private String tituloCurso;            

    // DESCRIPCION CURSO: Descripcion del curso
    @NotBlank
    private String descripcionCurso;      

    // CATEGORIA CURSO: Categoria del curso
    @NotBlank
    private String categoriaCurso;   

    // ID INSTRUCTOR CURSO: Instructor a cargo del curso
    @NotNull
    private int idInstructorCurso;         

}
