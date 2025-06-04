package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import lombok.Data;

/*------------------------------------------*/

@Data
public class EvaluacionUpdate {
    
    private int idEvaluacion;           // El id de la evaluación para buscarlo

    private Integer idCursoEvaluacion;          // El id el curso al que pertenece la evaluación              

    private String tipoEvaluacion;              // Tipo de evaluación

}
