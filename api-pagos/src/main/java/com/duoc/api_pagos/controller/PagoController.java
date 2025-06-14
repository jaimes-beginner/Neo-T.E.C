package com.duoc.api_pagos.controller;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_pagos.model.entity.Pago;
import com.duoc.api_pagos.model.request.PagoCreate;
import com.duoc.api_pagos.service.PagoService;
import jakarta.validation.Valid;

/*------------------------------------------*/

@RestController
@RequestMapping("/pay")
public class PagoController {
    
    // Atributos
    @Autowired
    private PagoService pagoServ;

    // Obtener todos los pagos 
    @GetMapping("/all")
    public List<Pago> obtenerTodos() {
        return pagoServ.obtenerTodos();
    }

    // Agregar-registrar un pago con PagoCreate
    @PostMapping("/add")
    public Pago agregarPago(@Valid @RequestBody PagoCreate datosCrear) {
        return pagoServ.registrarPago(datosCrear);
    }

}
