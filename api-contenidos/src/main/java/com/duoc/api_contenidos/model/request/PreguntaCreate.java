package com.duoc.api_contenidos.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*------------------------------------------*/

@Data
public class PreguntaCreate {
    
    // ENUNCIADO PREGUNTA: Enunciado de la pregunta
    @NotBlank
    private String enunciadoPregunta;       

    // OPCIONES PREGUNTA: Opciones-alternativas de la pregunta
    @NotBlank
    private String opcionesPregunta;        

    // RESP CORRECTA PREGUNTA: La respuesta correcta
    @NotBlank
    private String respCorrectaPregunta;    

}
