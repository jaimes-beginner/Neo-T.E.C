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

    // ID USUARIO: Identificador del usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;                 

    // NOMBRE USUARIO: Nombre del usuario
    @Column(nullable = false)
    private String nombreUsuario;        

    // CORREO USUARIO: Correo del usuario
    @Column(nullable = false, unique = true)
    private String correoUsuario;           

    // PASSWORD USUARIO: Constraseña del usuario
    @Column(nullable = false)
    private String passwordUsuario;    

    // ROL USUARIO: El rol del usuario 
    private String rolUsuario;

    // ESTADO USUARIO:  Estado del usuario
    private Boolean estadoUsuario;       

    // FECHA REGISTRO: Fecha del creación
    private Date fechaRegistro;           

}
