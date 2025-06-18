package com.duoc.api_resenas.repository;
/*------------------------------------------*/

// Importaciones

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.api_resenas.models.entities.Resenia;

/*------------------------------------------*/

@Repository
public interface ReseniaRepository extends JpaRepository<Resenia,Integer> {

    // Métodos para acceder a los datos de la base de datos...

}
