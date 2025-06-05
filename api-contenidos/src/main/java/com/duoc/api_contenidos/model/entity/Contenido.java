package com.duoc.api_contenidos.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;

/*------------------------------------------*/

// Importaciones
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
@Table(name = "contents")           // Nombre de la tabla
public class Contenido {
    
    @Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    private int idContenido;                // Identificador del contenido 

    @Column(nullable = false)
    private String tipoContenido;           // Video, pdf o quiz

    @Column(nullable = false)
    private String urlContenido;            // Url en donde está el contenido

    @Column(nullable = false)
    private String tituloContenido;         // Titulo del contenido
    
    @Column(nullable = false)
    private int idCursoContenido;           // Referencia al curso (está en otro microservicio)
    

    // Contenido puede tener muchas evaluaciones
    @JsonManagedReference
    @OneToMany(mappedBy = "contenido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evaluacion> listaEvaluaciones = new ArrayList<>();


}
