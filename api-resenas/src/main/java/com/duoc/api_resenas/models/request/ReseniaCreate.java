package com.duoc.api_resenas.models.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReseniaCreate {

    @NotBlank
    private String textoResenia;            // Cuerpo de la reseña

    @NotNull
    private int puntuacionResenia;          //Nota de la Reseña
}
