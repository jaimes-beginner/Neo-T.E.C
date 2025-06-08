package com.duoc.api_soporte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.api_soporte.model.entities.SoporteTicket;
import com.duoc.api_soporte.service.SoporteTicketService;

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


}
