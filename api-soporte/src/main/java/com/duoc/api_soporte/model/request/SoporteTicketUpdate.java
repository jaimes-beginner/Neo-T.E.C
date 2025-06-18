package com.duoc.api_soporte.model.request;

/*------------------------------------------*/

// Importaciones
import lombok.Data;

/*------------------------------------------*/

@Data
public class SoporteTicketUpdate {
    
    private int idTicket;           // Para buscar el ticket

    private String temaTicket;      // El tema del ticket

    private String estadoTicket;    // El estado del ticket

}
