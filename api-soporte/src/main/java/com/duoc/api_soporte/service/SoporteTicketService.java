package com.duoc.api_soporte.service;

import java.util.Date;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_soporte.model.entities.SoporteTicket;
import com.duoc.api_soporte.model.request.SoporteTicketCreate;
import com.duoc.api_soporte.model.request.SoporteTicketUpdate;
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

    /*--------------------------------------------------*/

    // Crear un nuevo ticket 
    public SoporteTicket crearTicket(SoporteTicketCreate datosCrear) {
        SoporteTicket nuevoTicket = new SoporteTicket();
        try {
        nuevoTicket.setFechaTicket(new Date());
        nuevoTicket.setTemaTicket(datosCrear.getIncidenteTicket());
        nuevoTicket.setEstadoTicket(datosCrear.getEstadoTicket());
        return soporteRepo.save(nuevoTicket);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    // Modificar el estado del ticket
    public void ModificarEstadoTicket(SoporteTicketUpdate datosModificar) {
        SoporteTicket soporteModificar = obtenerUno(datosModificar.getIdTicket());
        if(soporteModificar == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        soporteModificar.setEstadoTicket(datosModificar.getEstadoTicket());
        soporteRepo.save(soporteModificar);
    }

}
