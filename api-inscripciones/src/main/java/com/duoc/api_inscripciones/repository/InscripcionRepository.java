package com.duoc.api_inscripciones.repository;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.duoc.api_inscripciones.model.entity.Inscripcion;

/*------------------------------------------*/

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    // Métodos apra acceder a la base de datos
    List<Inscripcion> findAllByIdUsuarioInscripcion(int idUsuarioInscripcion);
    Boolean existsByIdUsuarioInscripcionAndIdCursoInscripcion(int idUsuario, int idCurso);
}
