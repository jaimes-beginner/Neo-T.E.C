package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class ContenidoUpdate {

    // ID CONTENIDO: Id del contenido para buscarlo
    @NotNull
    private int idContenido;           

    // TIPO CONTENIDO: El tipo de contenido   
    private String tipoContenido;       

    // URL CONTENIDO:  Url del contendio
    private String urlContenido;         

    // TITULO CONTENIDO: Titulo del contenido
    private String tituloContenido;        

    // ID CURSO CONTENIDO: Id del curso al que pertenece este contenido
    private Integer idCursoContenido;       

}
