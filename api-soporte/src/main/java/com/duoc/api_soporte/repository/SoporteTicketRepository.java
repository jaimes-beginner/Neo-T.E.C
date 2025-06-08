package com.duoc.api_soporte.repository;
/*------------------------------------------*/

// Importaciones
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.api_soporte.model.entities.SoporteTicket;
/*------------------------------------------*/
@Repository
public interface SoporteTicketRepository extends JpaRepository<SoporteTicket,Integer>{

    // Métodos para acceder a los datos de la base de datos...
    List<SoporteTicket> findByIdTicket(int idTicket);
    


}
