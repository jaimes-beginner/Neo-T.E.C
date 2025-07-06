package com.duoc.api_pagos.controller;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_pagos.assemblers.PagoModelAssembler;
import com.duoc.api_pagos.model.entity.Pago;
import com.duoc.api_pagos.model.request.PagoCreate;
import com.duoc.api_pagos.service.PagoService;
import jakarta.validation.Valid;

/*------------------------------------------*/

@RestController
@RequestMapping(value = "/pay", produces = MediaType.APPLICATION_JSON_VALUE)
public class PagoController {
    
    // Atributos
    @Autowired
    private PagoService pagoServ;

    // Atributos
    @Autowired
    private PagoModelAssembler assembler;

    // OBTENER UNO: Obtener un pago por su ID, con modificaciónes para el HATEOAS
    @GetMapping("/{idPago}")
    public ResponseEntity<EntityModel<Pago>> obtenerUno(@PathVariable int idPago) {
        Pago pago = pagoServ.obtenerUno(idPago);
        if (pago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(pago));
    }

    // OBTENER TODOS: Obtener todos los pagos, con modificaciónes para el HATEOAS
    @GetMapping("/all")
    public CollectionModel<EntityModel<Pago>> obtenerTodos() {
        List<EntityModel<Pago>> evaluaciones = pagoServ.obtenerTodos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(
            evaluaciones,
            linkTo(methodOn(PagoController.class).obtenerTodos()).withSelfRel()
        );
    }

    // AGREGAR PAGO: Agregar-registrar un pago con PagoCreate, con modificaciónes para el HATEOAS
    @PostMapping("/add")
    public ResponseEntity<EntityModel<Pago>> agregarPago(@Valid @RequestBody PagoCreate datosCrear) {
        Pago pago = pagoServ.registrarPago(datosCrear);
        return ResponseEntity.ok(assembler.toModel(pago));
    }

}
