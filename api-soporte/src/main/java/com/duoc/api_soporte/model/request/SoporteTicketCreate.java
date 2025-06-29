package com.duoc.api_soporte.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SoporteTicketCreate {

    @NotBlank
    private String estadoTicket;        // Opciones-Estado del Ticket

    @NotBlank
    private String incidenteTicket;     // Descripcion del problema

    @NotNull
    private int idUsuarioTicket;        // Id del usuario quien hace el ticket

    private String respuestaTicket;     // Respuesta del ticket

}
