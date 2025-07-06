package com.duoc.api_usuarios.model.request;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*------------------------------------------*/

@Data
public class UsuarioCreate {
    
    // NOMBRE USUARIO: Nombre del usuario
    @NotBlank
    private String nombreUsuario;        

    // CORREO USUARIO: Correo del usuario
    @NotBlank
    private String correoUsuario;     

    // PASSWORD USUARIO: Constraseña del usuario
    @NotBlank
    private String passwordUsuario;        

    // ROL USUARIO: Rol del usuario
    @NotBlank
    private String rolUsuario;            

}
