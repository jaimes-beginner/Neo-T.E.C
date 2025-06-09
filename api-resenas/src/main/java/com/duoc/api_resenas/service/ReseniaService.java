package com.duoc.api_resenas.service;
/*------------------------------------------*/

import java.util.Date;
// Importaciones
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.api_resenas.models.entities.Resenia;
import com.duoc.api_resenas.models.request.ReseniaCreate;
import com.duoc.api_resenas.repository.ReseniaRepository;
/*------------------------------------------*/
@Service
public class ReseniaService {

    @Autowired
    private ReseniaRepository reseniaRepo;

        // Obtener todas Reseñas
    public List<Resenia> obtenerTodos(){
        return reseniaRepo.findAll();
    }

    // Obtener una Reseña por ID 
    public Resenia obtenerUno(int id) {
        return reseniaRepo.findById(id).orElse(null);
    }

    // Deja una Reseña
    public Resenia dejarResena(ReseniaCreate resena) {
        Resenia resenia= new Resenia();
        resenia.setPuntuacionResenia(resena.getPuntuacionResenia());
        resenia.setTextoResenia(resena.getTextoResenia());
        resenia.setFechaResenia(new Date());
        return reseniaRepo.save(resenia);
    }
}
