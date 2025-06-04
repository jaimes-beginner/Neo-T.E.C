package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class ContenidoUpdate {

    @NotNull
    private int idContenido;            // Id del contenido para buscarlo

    private String tipoContenido;           // El tipo de contenido   

    private String urlContenido;            // Url del contendio

    private String tituloContenido;         // Titulo del contenido

    private Integer idCursoContenido;       // Id del curso al que pertenece este contenido

}
