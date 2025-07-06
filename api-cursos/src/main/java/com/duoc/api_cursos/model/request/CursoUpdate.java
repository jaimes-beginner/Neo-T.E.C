package com.duoc.api_cursos.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class CursoUpdate {

    // ID CURSO: Identificador del curos para buscarlo
    @NotNull
    private int idCurso;        

    // TITULO CURSO: Titulo de curso
    private String tituloCurso;         

    // DESCRIPCION CURSO: Descripcion del curso
    private String descripcionCurso;     

    // CATEGORIA CURSO: Categoria del curso
    private String categoriaCurso;      

    // ID INSTRUCTOR CURSO: Instructor a cargo del curso
    private Integer idInstructorCurso;          
    
}
