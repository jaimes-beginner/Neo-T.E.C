package com.duoc.api_soporte.controller;

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
import com.duoc.api_soporte.service.SoporteTicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tickets")
public class SoporteTicketController {
    
    @Autowired
    private SoporteTicketService soporteTiServe;

    // Obtener todos los tickets
    @GetMapping("/all")
    public List<SoporteTicket> obtenerTodos() {
        return soporteTiServe.obtenerTodos();
    }

    @GetMapping("/{id}")
    public SoporteTicket obtenerUno(@PathVariable int id) {
        return soporteTiServe.obtenerUno(id);
    }
  
    @PutMapping("/estado/{id}")
    public String cancelar(@PathVariable int id) {
        soporteTiServe.cancelar(id);
        return "Ticket cancelado!";
    }

    // Crear Ticket
    @PostMapping("/add")
    public String crearTicket(@RequestBody @Valid  SoporteTicketCreate soCreated) {
        soporteTiServe.dejarResena(soCreated);
        return "Ticket levantado con exito";
    }

}
