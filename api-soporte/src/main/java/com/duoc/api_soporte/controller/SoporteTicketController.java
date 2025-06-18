package com.duoc.api_soporte.controller;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_soporte.model.entities.SoporteTicket;
import com.duoc.api_soporte.model.request.SoporteTicketCreate;
import com.duoc.api_soporte.model.request.SoporteTicketUpdate;
import com.duoc.api_soporte.service.SoporteTicketService;
import jakarta.validation.Valid;

/*------------------------------------------*/

@RestController
@RequestMapping("/tickets")
public class SoporteTicketController {
    
    // Atributos
    @Autowired
    private SoporteTicketService soporteTiServe;

    // Obtener todos los tickets
    @GetMapping("/all")
    public List<SoporteTicket> obtenerTodos() {
        return soporteTiServe.obtenerTodos();
    }

    // Obtener un ticket por su id
    @GetMapping("/{id}")
    public SoporteTicket obtenerUno(@PathVariable int id) {
        return soporteTiServe.obtenerUno(id);
    }
  
    // Modificar un ticket
    @PutMapping("/updateStatus")
    public String cancelar(@RequestBody @Valid  SoporteTicketUpdate datosModificar) {
        soporteTiServe.ModificarEstadoTicket(datosModificar);
        return "Ticket modificado!";
    }

    // Crear un ticket
    @PostMapping("/add")
    public String crearTicket(@RequestBody @Valid  SoporteTicketCreate soCreated) {
        soporteTiServe.crearTicket(soCreated);
        return "Ticket levantado con exito";
    }

}
