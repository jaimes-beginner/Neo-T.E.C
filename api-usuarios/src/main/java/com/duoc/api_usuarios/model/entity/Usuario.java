package com.duoc.api_usuarios.model.entity;

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
@Table(name = "usuarios")       // Nombre de la tabla
public class Usuario {

    @Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    private int idUsuario;                  // Identificador del usuario

    @Column(nullable = false)
    private String nombreUsuario;           // Nombre del usuario

    @Column(nullable = false, unique = true)
    private String correoUsuario;           // Correo del usuario

    @Column(nullable = false)
    private String passwordUsuario;         // Constraseña del usuario

    @Column(nullable = true)
    private String rolUsuario;              // Rol del usuario

    private Boolean estadoUsuario;          // Estado del usuario

    private Date fechaRegistro;             // Fecha del creación

}
