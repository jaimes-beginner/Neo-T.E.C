package com.duoc.api_cursos.model.entity;

/*------------------------------------------*/

// Importaciones
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*------------------------------------------*/

@Data
@Entity
@Table(name = "cursos")          
public class Curso {    

    // ID CURSO: Identificador del curso
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCurso;                   

    // TITULO CURSO: Titulo del curso
    @Column(nullable = false)
    private String tituloCurso;             

    // DESCRIPCION CURSO: Descripción del curso
    @Column(nullable = false)
    private String descripcionCurso;     

    // CATEGORIA CURSO: Categoria del curso
    @Column(nullable = false)
    private String categoriaCurso;        

    // ESTADO CURSO: Estado del curso
    private String estadoCurso;         

    // CREACION CURSO: Fecha de creación del curso
    private Date creacionCurso;            

    // ID INSTRUCTOR CURSO: Instructor del curso
    @Column(nullable = false)
    private int idIntructorCurso;           
    
}
