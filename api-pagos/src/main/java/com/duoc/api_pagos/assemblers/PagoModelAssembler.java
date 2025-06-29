package com.duoc.api_pagos.assemblers;

/*------------------------------------------*/

// Importaciones
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.duoc.api_pagos.controller.PagoController;
import com.duoc.api_pagos.model.entity.Pago;

/*------------------------------------------*/

/* Haciendo el objeto PROGRESO con enlaces HATEOAS, para que devuelva los 
datos corresponsientes además de enlaces a recursos que estén relacionados */

@Component
public class PagoModelAssembler implements RepresentationModelAssembler<Pago, EntityModel<Pago>> {

    @Override
    public EntityModel<Pago> toModel(Pago pago) {
        return EntityModel.of(pago,

            /* Todos los métodos que se encuentren aquí deben estar bien definidos
            además de que deben coincidir en su FIRMA, es decir: que estén exactamente iguales en nombre 
            del método, tipo de datos, etc. Para que methodOn pueda ejecutarlos correctamente */ 

            // Self link
            linkTo(methodOn(PagoController.class).obtenerUno(pago.getIdPago())).withSelfRel(),

            // Enlace al usuario relacionado a la inscripcion
            Link.of("http://localhost:8080/users/" + pago.getIdUsuarioPago()).withRel("usuario"),

            // Enlace a la inscripción 
            Link.of("http://localhost:8083/inscriptions/" + pago.getIdCursoPago()).withRel("inscripcion")

        );
    }
}