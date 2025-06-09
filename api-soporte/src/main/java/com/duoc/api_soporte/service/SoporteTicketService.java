package com.duoc.api_soporte.service;

/*------------------------------------------*/

// Importaciones
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.duoc.api_soporte.model.entities.SoporteTicket;
import com.duoc.api_soporte.model.request.SoporteTicketCreate;
import com.duoc.api_soporte.repository.SoporteTicketRepository;

/*------------------------------------------*/

@Service
public class SoporteTicketService {

    // Autowired
    @Autowired
    private SoporteTicketRepository soporteRepo;

    // Obtener todos los Tickets
    public List<SoporteTicket> obtenerTodos(){
        return soporteRepo.findAll();
    }

    // Obtener uno por ID del ticket
    public SoporteTicket obtenerUno(int id) {
        return soporteRepo.findById(id).orElse(null);
    }

    // Cambiar Estado de los tickets a Cancelado una vez completado
    public void cancelar(int id) {
    SoporteTicket soporteTi = obtenerUno(id);
    if(soporteTi == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    soporteTi.setEstadoTicket("Canceled");
    soporteRepo.save(soporteTi);
    }
    
    // Deja un Ticket
    public SoporteTicket dejarResena(SoporteTicketCreate sopT) {
        SoporteTicket so= new SoporteTicket();
        so.setEstadoTicket(sopT.getEstadoTicket());
        so.setTemaTicket(sopT.getIncidenteTicket());
        return soporteRepo.save(so);
    }



}
