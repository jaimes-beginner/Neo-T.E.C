package com.duoc.api_usuarios.service;

/*------------------------------------------*/

// Importaciones
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_usuarios.model.entity.Usuario;
import com.duoc.api_usuarios.model.request.UsuarioCreate;
import com.duoc.api_usuarios.model.request.UsuarioUpdate;
import com.duoc.api_usuarios.repository.UsuarioRepository;

/*------------------------------------------*/

@Service
public class UsuarioService {
 
    // Atributos
    @Autowired
    private UsuarioRepository usuarioRepo;

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return usuarioRepo.findAll();
    }

    // Obtener todos los usuarios activos
    public List<Usuario> obtenerActivos() {
        return usuarioRepo.findAllByEstadoUsuario(true);
    }

    // Obtener un usuario
    public Usuario obtenerUno(int id) {
        return usuarioRepo.findById(id).orElse(null);
    }

    // Agregar un usuario
    public Usuario agregar(UsuarioCreate datosCrear) {
        Usuario usuario = new Usuario();
        try {
            usuario.setEstadoUsuario(true);
            usuario.setFechaRegistro(new Date());
            usuario.setNombreUsuario(datosCrear.getNombreUsuario());
            usuario.setCorreoUsuario(datosCrear.getCorreoUsuario());
            usuario.setPasswordUsuario(hashPassword(datosCrear.getPasswordUsuario()));
            usuario.setRolUsuario(datosCrear.getRolUsuario());
            return usuarioRepo.save(usuario);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    // Hashear la constraña del usuario
    private String hashPassword(String password){
        BCryptPasswordEncoder hasheador = new BCryptPasswordEncoder();
        return hasheador.encode(password);
    }

    // Eliminar un usuario por su id
    public void eliminar(int id) {
        Usuario usuarioEliminar = obtenerUno(id);
        if(usuarioEliminar == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        usuarioEliminar.setEstadoUsuario(false);
        usuarioRepo.save(usuarioEliminar);
    }

    // Modificar la información de un usuario por su id (datosModificar)
    public Usuario modificar(UsuarioUpdate datosModificar) {
        Usuario usuarioModificar = obtenerUno(datosModificar.getIdUsuario());
        if(usuarioModificar == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(datosModificar.getNombreUsuario() != null) {
            usuarioModificar.setNombreUsuario(datosModificar.getNombreUsuario());
        }
        if(datosModificar.getCorreoUsuario() != null) {
            usuarioModificar.setCorreoUsuario(datosModificar.getCorreoUsuario());
        }
        if(datosModificar.getPasswordUsuario() != null) {
            usuarioModificar.setPasswordUsuario(datosModificar.getPasswordUsuario());
        }
        return usuarioRepo.save(usuarioModificar);
    }

}
