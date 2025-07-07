package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class ContenidoCreate {
    
    // TIPO CONTENIDO: El tipo de contenido  
    @NotBlank
    private String tipoContenido;      

    // URL CONTENIDO: Url del contendio
    @NotBlank
    private String urlContenido;       

    // TITULO CONTENIDO: Titulo del contenido
    @NotBlank
    private String tituloContenido;     

    // ID CURSO CONTENIDO: Id del curso al que pertenece este contenido
    @NotNull
    private int idCursoContenido;        

}
