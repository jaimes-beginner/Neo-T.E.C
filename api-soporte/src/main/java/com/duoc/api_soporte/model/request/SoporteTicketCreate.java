package com.duoc.api_soporte.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SoporteTicketCreate {
     

    @NotBlank
    private String estadoTicket;        // Opciones-Estado del Ticket

    @NotBlank
    private String incidenteTicket;    // Descripcion del problema





}
