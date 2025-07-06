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
@Table(name="ticket")            
public class SoporteTicket {

    // ID TICKET: Identificador del Ticket 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicket;              

    // ID USUARIO TICKET: Id del usuario quien hace el ticket
    @Column(nullable = false)
    private int idUsuarioTicket;      

    // TEMA TICKET: Tema del Ticket 
    @Column(nullable = false)
    private String temaTicket;     

    // ESTADO TICKET: Estado del Ticket 
    @Column(nullable = false)
    private String estadoTicket;  

    // RESPUESTA TICKET: Respuesta del ticket
    @Column(nullable = false)
    private String respuestaTicket;   
    
    // FECHA TICKET: Fecha en la que se hizo el ticket
    private Date fechaTicket;        

}
