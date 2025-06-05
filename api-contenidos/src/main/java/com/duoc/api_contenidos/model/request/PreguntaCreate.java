package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*------------------------------------------*/

@Data
public class PreguntaCreate {
    
    @NotBlank
    private String enunciadoPregunta;       // Enunciado de la pregunta

    @NotBlank
    private String opcionesPregunta;        // Opciones-alternativas de la pregunta

    @NotBlank
    private String respCorrectaPregunta;    // La respuesta correcta

}
