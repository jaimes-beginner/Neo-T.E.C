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

    // Obtener todas las reseñas
    public List<Resenia> obtenerTodos(){
        return reseniaRepo.findAll();
    }

    // Obtener una reseña por ID 
    public Resenia obtenerUno(int id) {
        return reseniaRepo.findById(id).orElse(null);
    }

    // Deja una reseña
    public Resenia dejarResena(ReseniaCreate resena) {
        Resenia resenia = new Resenia();
        try {
            resenia.setPuntuacionResenia(resena.getPuntuacionResenia());
            resenia.setTextoResenia(resena.getTextoResenia());
            resenia.setFechaResenia(new Date());
            return reseniaRepo.save(resenia);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
