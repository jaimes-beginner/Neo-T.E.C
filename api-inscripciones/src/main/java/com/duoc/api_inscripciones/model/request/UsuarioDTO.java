package com.duoc.api_inscripciones.model.request;

/*------------------------------------------*/

// Importaciones
import lombok.Data;

/*------------------------------------------*/

@Data
public class UsuarioDTO {
    
    private int idUsuario; 

    private String nombreUsuario; 

    private String correoUsuario;  

    private Boolean estadoUsuario; 

}
