package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*------------------------------------------*/

@Data
public class EvaluacionCreate {
    
    // TIPO EVALUACION: Tipo de evaluación
    @NotBlank
    private String tipoEvaluacion;       

    // ID CONTENIDO: Id del contenido en donde pertenece esta evaluación
    private int idContenido;              

    // LISTA PREGUNTAS: Las preguntas que tiene la evaluacion
    private List<PreguntaCreate> listaPreguntas;    

}
