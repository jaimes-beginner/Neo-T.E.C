package com.duoc.api_pagos.service;

/*------------------------------------------*/

// Importaciones
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_pagos.model.entity.Pago;
import com.duoc.api_pagos.model.request.PagoCreate;
import com.duoc.api_pagos.repository.PagoRepository;

/*------------------------------------------*/

@Service
public class PagoService {
    
    // Atributos
    @Autowired
    private PagoRepository pagoRepo;

    // Atributos
    @Autowired
    private WebClient usuarioWebClient;

    // Atributos
    @Autowired
    private WebClient cursoWebClient;

    // OBTENER UNO: Obtener un pago por su ID
    public Pago obtenerUno(int idPago) {
        return pagoRepo.findById(idPago).orElse(null);
    }

    // OBTENER TODOS: Obtener todos los pagos
    public List<Pago> obtenerTodos() {
        return pagoRepo.findAll();
    }

    // REGISTRAR PAGO: Registrar un pago con los datos de usuario y usuario (si existen)
    public Pago registrarPago(PagoCreate datosCrear) {

        try {

            // Ver si el usuario existe
            usuarioWebClient.get().uri("/users/" + datosCrear.getIdUsuarioPago()).retrieve().toBodilessEntity().block(); 

            // Lo mismo pero con el curso
            cursoWebClient.get().uri("/courses/" + datosCrear.getIdCursoPago()).retrieve().toBodilessEntity().block();   

            // Setteando los datos en caso de que estén bien
            Pago pago = new Pago();
            pago.setFechaPago(new Date());
            pago.setIdUsuarioPago(datosCrear.getIdUsuarioPago());
            pago.setIdCursoPago(datosCrear.getIdCursoPago());
            pago.setMontoPago(datosCrear.getMontoPago());
            pago.setIdInscripcion(datosCrear.getIdInscripcion());

            return pagoRepo.save(pago);
            
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
