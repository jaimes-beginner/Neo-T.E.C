package com.duoc.api_usuarios.model.request;

/*------------------------------------------*/

// Importaciones
import lombok.Data;

/*------------------------------------------*/

@Data
public class UsuarioUpdate {
    
    // ID USUARIO: Para buscar al usuario que se quiere modificar
    private int idUsuario;          

    // NOMBRE USUARIO: Nombre del usuario
    private String nombreUsuario;          

    // CORREO USUARIO: Correo del usuario
    private String correoUsuario;      

    // PASSWORD USUARIO: Constraseña del usuario
    private String passwordUsuario;        


}
