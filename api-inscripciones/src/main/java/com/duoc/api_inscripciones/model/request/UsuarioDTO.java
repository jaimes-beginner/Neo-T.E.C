package com.duoc.api_inscripciones.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*------------------------------------------*/

// Importaciones
import lombok.Data;

/*------------------------------------------*/

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO {
    
    // ID USUARIO: Id del usuario para buscarlo
    private int idUsuario;

}
