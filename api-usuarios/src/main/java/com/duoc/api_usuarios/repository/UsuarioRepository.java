package com.duoc.api_usuarios.repository;

import java.util.List;

/*------------------------------------------*/

// Importaciones
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.duoc.api_usuarios.model.entity.Usuario;

/*------------------------------------------*/

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Métodos para buscar en la base de datos...
    List<Usuario> findAllByEstadoUsuario(Boolean estadoUsuario);
    Usuario findAByCorreoUsuario(String correoUsuario);
    Boolean existsByCorreoUsuario(String correoUsuario);
    
}
