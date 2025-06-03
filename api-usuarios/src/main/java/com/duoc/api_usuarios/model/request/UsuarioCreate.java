package com.duoc.api_usuarios.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*------------------------------------------*/

@Data
public class UsuarioCreate {
    
    @NotBlank
    private String nombreUsuario;           // Nombre del usuario

    @NotBlank
    private String correoUsuario;           // Correo del usuario

    @NotBlank
    private String passwordUsuario;         // Constraseña del usuario

    @NotBlank
    private String rolUsuario;              // Rol del usuario

}
