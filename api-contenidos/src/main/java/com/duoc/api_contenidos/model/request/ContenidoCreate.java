package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class ContenidoCreate {
    
    @NotBlank
    private String tipoContenido;           // El tipo de contenido   

    @NotBlank
    private String urlContenido;            // Url del contendio

    @NotBlank
    private String tituloContenido;         // Titulo del contenido

    @NotNull
    private int idCursoContenido;           // Id del curso al que pertenece este contenido

}
