package com.duoc.api_inscripciones.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*------------------------------------------*/

// Importaciones
import lombok.Data;

/*------------------------------------------*/

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO {
    
    private int idUsuario;
    private String nombreUsuario;
    private String correoUsuario;
    private String passwordUsuario;
    private String rolUsuario;
    private boolean estadoUsuario;
    private String fechaRegistro;

}
