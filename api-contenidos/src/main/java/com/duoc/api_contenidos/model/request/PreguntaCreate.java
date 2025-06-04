package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class PreguntaCreate {
    
    @NotNull
    private Integer idEvaluacionPregunta;   // En que evaluación pertenece esta pregúnta

    @NotBlank
    private String enunciadoPregunta;       // Enunciado de la pregunta

    @NotBlank
    private String opcionesPregunta;        // Opciones-alternativas de la pregunta

    @NotBlank
    private String respCorrectaPregunta;    // La respuesta correcta

}
