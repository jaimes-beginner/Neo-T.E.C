package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class EvaluacionCreate {
    
    @NotNull
    private int idCursoEvaluacion;          // El id del curso al que pertenece este evaluación

    @NotBlank
    private String tipoEvaluacion;          // Tipo de evaluación

}
