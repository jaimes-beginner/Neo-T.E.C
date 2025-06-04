package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class PreguntaUpdate {

    @NotNull
    private int idPregunta;     // Id solo para encontrar la pregúnta

    private Integer idEvaluacionPregunta;   // En que evaluación pertenece esta pregúnta

    private String enunciadoPregunta;       // Enunciado de la pregunta

    private String opcionesPregunta;        // Opciones-alternativas de la pregunta

    private String respCorrectaPregunta;    // La respuesta correcta

}
