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

/*------------------------------------------*/

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {
    
    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,
            linkTo(methodOn(UsuarioController.class).obtenerUno(usuario.getIdUsuario())).withSelfRel(),
            linkTo(methodOn(UsuarioController.class).obtenerTodos()).withRel("Todos los usuarios")
        );
    }
}
