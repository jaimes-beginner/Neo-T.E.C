package com.duoc.api_contenidos.repository;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.duoc.api_contenidos.model.entity.Evaluacion;

/*------------------------------------------*/

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {
    
    // Métodos para acceder a los datos de la base de datos...
    List<Evaluacion> findByContenido_IdContenido(int idContenido);
    
}
