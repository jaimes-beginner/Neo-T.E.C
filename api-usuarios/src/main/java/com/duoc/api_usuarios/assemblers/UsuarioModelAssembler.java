package com.duoc.api_usuarios.assemblers;

/*------------------------------------------*/

// Importaciones
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.duoc.api_usuarios.controller.UsuarioController;
import com.duoc.api_usuarios.model.entity.Usuario;
import org.springframework.hateoas.Link;

/*------------------------------------------*/

/* Haciendo el objeto USUARIO con enlaces HATEOAS, para que devuelva los 
datos corresponsientes además de enlaces a recursos que estén relacionados */

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,

            /* Todos los métodos que se encuentren aquí deben estar bien definidos
            además de que deben coincidir en su FIRMA, es decir: que estén exactamente iguales en nombre 
            del método, tipo de datos, etc. Para que methodOn pueda ejecutarlos correctamente */ 

            // Self link 
            linkTo(methodOn(UsuarioController.class).obtenerUno(usuario.getIdUsuario())).withSelfRel(),

            // Link para que el usuario se inscriba
            Link.of("http://localhost:8083/inscriptions/add").withRel("inscripciones"),

            // Link para que el usuario pueda hacer una reseña
            Link.of("http://localhost:8086/resenia/add").withRel("resenas"),

            // Link para que el usuario pueda hacer un ticket
            Link.of("http://localhost:8085/tickets/add").withRel("soporte")
            
        );
    }
}
