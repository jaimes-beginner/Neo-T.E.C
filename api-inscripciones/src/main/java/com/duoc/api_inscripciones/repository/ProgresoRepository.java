package com.duoc.api_inscripciones.repository;

import java.util.Optional;

/*------------------------------------------*/

// Importaciones
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.duoc.api_inscripciones.model.entity.Progreso;

/*------------------------------------------*/

@Repository
public interface ProgresoRepository extends JpaRepository<Progreso, Integer> {
    
    // Métodos para acceder a datos de la base de datos...
    Optional<Progreso> findByIdUsuarioIdCuros(int idUsuarioProgreso, int idCursoProgreso);
}
