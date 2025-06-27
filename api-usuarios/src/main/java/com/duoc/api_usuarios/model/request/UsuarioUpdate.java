package com.duoc.api_usuarios.model.request;

/*------------------------------------------*/

// Importaciones
import lombok.Data;

/*------------------------------------------*/

@Data
public class UsuarioUpdate {
    
    private int idUsuario;          // Para buscar al usuario que se quiere modificar

    private String nombreUsuario;           // Nombre del usuario

    private String correoUsuario;           // Correo del usuario

    private String passwordUsuario;         // Constraseña del usuario


}
