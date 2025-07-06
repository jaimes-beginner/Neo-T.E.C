package com.duoc.api_pagos.model.request;


/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*------------------------------------------*/

@Data
public class PagoCreate {
    
    // ID CURSO PAGO: El id del curso  
    @NotNull
    private int idCursoPago;                   
    
    // ID USUARIO PAGO: El id del usuario 
    @NotNull
    private int idUsuarioPago;        

    // MONTO PAGO: Monto que se está pagando
    @NotNull
    private Double montoPago;         

    // ID INSCRIPCION: la inscripcion que se va a pagar
    @NotNull
    private int idInscripcion;

}
