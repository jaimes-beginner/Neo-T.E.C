package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import lombok.Data;

/*------------------------------------------*/

@Data
public class EvaluacionUpdate {
    
    // ID EVALUACION: El id de la evaluación para buscarlo
    private int idEvaluacion;           

    // ID CURSO EVALUACION: El id el curso al que pertenece la evaluación   
    private Integer idCursoEvaluacion;                     

    // TIPO EVALUACION: Tipo de evaluación
    private String tipoEvaluacion;              

}
