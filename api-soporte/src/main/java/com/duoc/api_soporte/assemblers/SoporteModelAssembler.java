package com.duoc.api_soporte.assemblers;

/*------------------------------------------*/

// Importaciones
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.duoc.api_soporte.controller.SoporteTicketController;
import com.duoc.api_soporte.model.entities.SoporteTicket;
import org.springframework.hateoas.Link;

/*------------------------------------------*/

/* Haciendo el objeto SOPORTE con enlaces HATEOAS, para que devuelva los 
datos corresponsientes además de enlaces a recursos que estén relacionados */

@Component
public class SoporteModelAssembler implements RepresentationModelAssembler<SoporteTicket, EntityModel<SoporteTicket>> {

    @Override
    public EntityModel<SoporteTicket> toModel(SoporteTicket ticket) {
        return EntityModel.of(ticket,

            /* Todos los métodos que se encuentren aquí deben estar bien definidos
            además de que deben coincidir en su FIRMA, es decir: que estén exactamente iguales en nombre 
            del método, tipo de datos, etc. Para que methodOn pueda ejecutarlos correctamente */ 

            // Self link
            linkTo(methodOn(SoporteTicketController.class).obtenerUno(ticket.getIdTicket())).withSelfRel(),

            // Link al usuario que creó el ticket 
            Link.of("http://localhost:8085/support/user/" + ticket.getIdUsuarioTicket()).withRel("usuario"),

            // Link a la respuesta del ticket, si es que la respondieron
            Link.of("http://localhost:8085/support/" + ticket.getIdTicket()).withRel("ticket_respuesta")
            
        );
    }
}