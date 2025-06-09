package com.duoc.api_resenas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.api_resenas.models.entities.Resenia;
import com.duoc.api_resenas.models.request.ReseniaCreate;
import com.duoc.api_resenas.service.ReseniaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/resenia")
public class ReseniaController {
    @Autowired
    private ReseniaService reseniaSer;

     // Obtener todos las reseñias
    @GetMapping("/all")
    public List<Resenia> obtenerTodos() {
        return reseniaSer.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Resenia obtenerUno(@PathVariable int id) {
        return reseniaSer.obtenerUno(id);
    }

    // Crear Reseña
    @PostMapping("/add")
    public String crearResenia(@RequestBody @Valid  ReseniaCreate reCreate) {
        reseniaSer.dejarResena(reCreate);
        return "Reseña publicada con exito";
    }
    
}
