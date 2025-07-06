package com.duoc.api_resenas.service;

/*------------------------------------------*/

// Importaciones
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_resenas.models.entities.Resenia;
import com.duoc.api_resenas.models.request.ReseniaCreate;
import com.duoc.api_resenas.repository.ReseniaRepository;

/*------------------------------------------*/

@Service
public class ReseniaService {

    // Atributos
    @Autowired
    private ReseniaRepository reseniaRepo;

    // OBTENER TODOS: Obtener todas las reseñas
    public List<Resenia> obtenerTodos(){
        return reseniaRepo.findAll();
    }

    // OBTENER UNO: Obtener una reseña por ID 
    public Resenia obtenerUno(int idResenia) {
        return reseniaRepo.findById(idResenia).orElse(null);
    }

    // DEJAR RESENIA: Dejar una reseña
    public Resenia dejarResena(ReseniaCreate resena) {
        Resenia resenia = new Resenia();

        // Validnado que la putuación de la reseña sea correcta
        if(resena.getPuntuacionResenia() < 1 || resena.getPuntuacionResenia() > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La puntuación de la reseña debe ser entre 1 y 5");
        }

        // Setteando los datos de la reseña
        try {
            resenia.setIdUsuarioResenia(resena.getIdUsuarioResenia());
            resenia.setIdCursoResenia(resena.getIdCursoResenia());
            resenia.setPuntuacionResenia(resena.getPuntuacionResenia());
            resenia.setTextoResenia(resena.getTextoResenia());
            resenia.setFechaResenia(new Date());
            return reseniaRepo.save(resenia);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
