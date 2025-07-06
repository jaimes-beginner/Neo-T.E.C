package com.duoc.api_inscripciones.model.request;

/*------------------------------------------*/

// Importaciones
import java.util.Date;
import lombok.Data;

/*------------------------------------------*/

@Data
public class PagoRequestDTO {

    // ID CURSO PAGO: id del curso que se va pagar
    private int idCursoPago;

    // ID USUARIO PAGO: id del usuario que va a pagar
    private int idUsuarioPago;

    // MONTO PAGO: el monto que se va a pagar
    private Double montoPago;

    // FECHA PAGO: fecha en que se la realiza el pago
    private Date fechaPago;

    // ID INSCRIPCION: hacer referencia a que inscripcion se está procesando
    private int idInscripcion; 
}