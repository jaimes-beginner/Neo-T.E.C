package com.duoc.api_pagos.model.entity;

/*------------------------------------------*/

// Importaciones
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*------------------------------------------*/

@Data
@Entity
@Table(name = "payments")           // Nombre de la tabla
public class Pago {

    // ID PAGO: Identificador del pago
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPago;                    

    // ID CURSO PAGO: El id del curso al que se le hizo el pago
    @Column(nullable = false)
    private int idCursoPago;               
    
    // ID USUARIO PAGO: El id del usuario que está compranddo el curso
    @Column(nullable = false)
    private int idUsuarioPago;             

    // MONTO PAGO: El monto que se está pagando por el curso  
    @Column(nullable = false)
    private Double montoPago;              

    // FECHA PAGO: Fecha del pago
    private Date fechaPago;                 

    // ID INSCRIPCION: la inscripcion que se va a pagar
    @Column(nullable = false)
    private int idInscripcion;

}
