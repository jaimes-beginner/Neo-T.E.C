package com.duoc.api_pagos.model.request;


/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class PagoCreate {
    
    @NotNull
    private int idCursoPago;            // El id del curso              
    
    @NotNull
    private int idUsuarioPago;          // El id del usuario 

    @NotNull
    private Double montoPago;           // Monto que se está pagando

}
