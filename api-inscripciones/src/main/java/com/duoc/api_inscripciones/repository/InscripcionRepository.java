package com.duoc.api_inscripciones.repository;

import java.util.List;

/*------------------------------------------*/

// Importaciones
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.duoc.api_inscripciones.model.entity.Inscripcion;

/*------------------------------------------*/

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    // Métodos apra acceder a la base de datos
    List<Inscripcion> findAllByIdUsuarioInscripcion(int idUsuarioInscripcion);
}
