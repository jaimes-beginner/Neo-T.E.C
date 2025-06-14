package com.duoc.api_pagos.repository;

/*------------------------------------------*/

// Importaciones
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.duoc.api_pagos.model.entity.Pago;

/*------------------------------------------*/

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer>{
    
    // Métodos para acceder a los datos de la base de datos...

}
