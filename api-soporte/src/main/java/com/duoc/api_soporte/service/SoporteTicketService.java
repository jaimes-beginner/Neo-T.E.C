package com.duoc.api_soporte.service;

/*------------------------------------------*/

// Importaciones
import java.util.Date;
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



    // OBTENER TODOS: devuelve todos los tickets de soporte
    public List<SoporteTicket> obtenerTodos(){
        return soporteRepo.findAll();
    }



    // OBTENER UNO: devuelve un ticket de soporte según su ID
    public SoporteTicket obtenerUno(int id) {
        return soporteRepo.findById(id).orElse(null);
    }



    // CREAR TICKET: crea un ticket de soporte según los datos (datosCrear)
    public SoporteTicket crearTicket(SoporteTicketCreate datosCrear) {
        SoporteTicket nuevoTicket = new SoporteTicket();
        try {
            nuevoTicket.setFechaTicket(new Date());
            nuevoTicket.setIdUsuarioTicket(datosCrear.getIdUsuarioTicket());
            nuevoTicket.setTemaTicket(datosCrear.getIncidenteTicket());
            nuevoTicket.setEstadoTicket(datosCrear.getEstadoTicket());
            return soporteRepo.save(nuevoTicket);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }



    // MODIFICAR TICKET: modifica un ticket según sus datos (datosModificar)
    public SoporteTicket ModificarTicket(SoporteTicketUpdate datosModificar) {
        SoporteTicket soporteModificar = obtenerUno(datosModificar.getIdTicket());
        if(soporteModificar == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        soporteModificar.setTemaTicket(datosModificar.getTemaTicket());
        soporteModificar.setEstadoTicket(datosModificar.getEstadoTicket());
        soporteModificar.setRespuestaTicket(datosModificar.getRespuestaTicket());
        return soporteRepo.save(soporteModificar);
    }


}
