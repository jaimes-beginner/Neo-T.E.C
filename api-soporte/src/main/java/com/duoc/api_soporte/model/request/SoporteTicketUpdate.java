package com.duoc.api_soporte.model.request;

/*------------------------------------------*/

// Importaciones
import lombok.Data;

/*------------------------------------------*/

@Data
public class SoporteTicketUpdate {
    
    // ID TICKET: Para buscar el ticket
    private int idTicket;         

    // TEMA TICKET: El tema del ticket
    private String temaTicket;    

    // ESTADO TICKET: El estado del ticket
    private String estadoTicket;  

    // RESPUESTA TICKET: Breve respuesta del ticket 
    private String respuestaTicket;
}
