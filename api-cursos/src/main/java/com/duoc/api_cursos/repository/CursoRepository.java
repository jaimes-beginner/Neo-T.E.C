package com.duoc.api_cursos.repository;

import java.util.List;

/*------------------------------------------*/

// Importaciones
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.duoc.api_cursos.model.entity.Curso;

/*------------------------------------------*/

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{
    
    // Métodos para llamar a la base de datos...
    List<Curso> findAllByEstadoCurso(String estadoCurso);
}
