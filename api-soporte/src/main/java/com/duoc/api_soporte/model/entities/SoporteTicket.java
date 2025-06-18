package com.duoc.api_soporte.model.entities;
/*------------------------------------------*/

// Importaciones
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
/*------------------------------------------*/

@Data
@Entity
@Table(name="ticket")                  // Nombre de la tabla
public class SoporteTicket {

    @Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    private int idTicket;                // Identificador del Ticket 

    @Column(nullable = false)
    private String temaTicket;          // Tema del Ticket 

    @Column(nullable = false)
    private String estadoTicket;        // Estado del Ticket 
    
    private Date fechaTicket;           // Fecha en la que se hizo el ticket


}
