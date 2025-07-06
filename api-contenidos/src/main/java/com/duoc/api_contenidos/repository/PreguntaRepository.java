package com.duoc.api_contenidos.repository;

/*------------------------------------------*/

// Importaciones
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.duoc.api_contenidos.model.entity.Pregunta;

/*------------------------------------------*/

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Integer>{
    
}
