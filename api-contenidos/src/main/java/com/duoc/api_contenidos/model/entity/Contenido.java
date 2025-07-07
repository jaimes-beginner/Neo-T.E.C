package com.duoc.api_contenidos.model.entity;

/*------------------------------------------*/

// Importaciones
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/*------------------------------------------*/

@Data
@Entity
@Table(name = "contents")      
public class Contenido {
    
    // ID CONTENIDO: Identificador del contenido 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContenido;             

    // TIPO CONTENIDO: Video, pdf o quiz
    @Column(nullable = false)
    private String tipoContenido;   

    // URL CONTENIDO: Url en donde está el contenido
    @Column(nullable = false)
    private String urlContenido;         

    // TITULO CONTENIDO: Titulo del contenido
    @Column(nullable = false)
    private String tituloContenido;       
    
    // ID CURSO CONTENIDO: Referencia al curso (está en otro microservicio)
    @Column(nullable = false)
    private int idCursoContenido;          
    
    // LISTA EVALUACIONES: Contenido puede tener muchas evaluaciones
    @JsonManagedReference
    @OneToMany(mappedBy = "contenido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evaluacion> listaEvaluaciones = new ArrayList<>();

}
