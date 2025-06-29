package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*------------------------------------------*/

@Data
public class EvaluacionCreate {
    
    @NotBlank
    private String tipoEvaluacion;          // Tipo de evaluación

    private int idContenido;                // Id del contenido en donde pertenece esta evaluación

    private List<PreguntaCreate> listaPreguntas;     // Las preguntas que tiene la evaluacion

}
