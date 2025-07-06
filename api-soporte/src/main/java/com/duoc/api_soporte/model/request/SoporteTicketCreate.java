package com.duoc.api_soporte.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SoporteTicketCreate {

    // ESTADO TICKET: Opciones-Estado del Ticket
    @NotBlank
    private String estadoTicket;       

    // INCIDENTE TICKET: Descripcion del problema
    @NotBlank
    private String incidenteTicket;     

    // ID USUARIO TICKET: Id del usuario quien hace el ticket
    @NotNull
    private int idUsuarioTicket;       

    // RESPUESTA TICKET: Respuesta del ticket
    @NotNull
    private String respuestaTicket;     

}
