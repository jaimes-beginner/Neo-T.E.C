package com.duoc.api_soporte.controller;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_soporte.assemblers.SoporteModelAssembler;
import com.duoc.api_soporte.model.entities.SoporteTicket;
import com.duoc.api_soporte.model.request.SoporteTicketCreate;
import com.duoc.api_soporte.model.request.SoporteTicketUpdate;
import com.duoc.api_soporte.service.SoporteTicketService;
import jakarta.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/*------------------------------------------*/

@RestController
@RequestMapping("/tickets")
public class SoporteTicketController {
    
    
    // Atributos
    @Autowired
    private SoporteTicketService soporteTiServe;

    // Atributos
    @Autowired
    private SoporteModelAssembler assembler;



    // Obtener todos los tickets con HATEOAS
    @GetMapping("/all")
    public CollectionModel<EntityModel<SoporteTicket>> obtenerTodos() {
        List<EntityModel<SoporteTicket>> tickets = soporteTiServe.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tickets,
                linkTo(methodOn(SoporteTicketController.class).obtenerTodos()).withSelfRel());
    }



    // Obtener un ticket por su ID con HATEOAS
    @GetMapping("/{id}")
    public EntityModel<SoporteTicket> obtenerUno(@PathVariable int id) {
        SoporteTicket ticket = soporteTiServe.obtenerUno(id);
        return assembler.toModel(ticket);
    }
  


    // Modificar un ticket
    @PutMapping("/updateStatus")
    public SoporteTicket cancelar(@RequestBody @Valid  SoporteTicketUpdate datosModificar) {
        return soporteTiServe.ModificarTicket(datosModificar);
    }



    // Crear un ticket
    @PostMapping("/add")
    public String crearTicket(@RequestBody @Valid  SoporteTicketCreate soCreated) {
        soporteTiServe.crearTicket(soCreated);
        return "Ticket levantado con exito";
    }

}
