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
@Table(name = "cursos")             // Nombre de la tabla
public class Curso {    

    @Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    private int idCurso;                    // Identificador del curso

    @Column(nullable = false)
    private String tituloCurso;             // Titulo del curso

    @Column(nullable = false)
    private String descripcionCurso;        // Descripción del curso

    @Column(nullable = false)
    private String categoriaCurso;          // Categoria del curso

    private String estadoCurso;            // Estado del curso

    private Date creacionCurso;             // Fecha de creación del curso

    @Column(nullable = false)
    private int idIntructorCurso;           // Instructor del curso
    
}
