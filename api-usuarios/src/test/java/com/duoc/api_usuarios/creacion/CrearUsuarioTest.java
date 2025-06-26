package com.duoc.api_usuarios.creacion;

/*------------------------------------------------*/

// Importaciones
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.duoc.api_usuarios.model.entity.Usuario;
import com.duoc.api_usuarios.model.request.UsuarioCreate;
import com.duoc.api_usuarios.repository.UsuarioRepository;
import com.duoc.api_usuarios.service.UsuarioService;

/*------------------------------------------------*/

public class CrearUsuarioTest {

 @Mock
    private UsuarioRepository usuarioRepo;

    @InjectMocks
    private UsuarioService usuarioServ;

    // Para inicializar los mocks antes de cada test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    // Este test verifica que se cree correctamente un usuario si el correo no existe
    @Test
    void testCreacionUsuario() {

        // Una simulacion de crear a un usuario
        UsuarioCreate datosCrear = new UsuarioCreate();
        datosCrear.setNombreUsuario("Usuario");
        datosCrear.setCorreoUsuario("usuario@email.com");
        datosCrear.setPasswordUsuario("123456");
        datosCrear.setRolUsuario("ESTUDIANTE");

        // Simulamos que el correo NO está registrado
        when(usuarioRepo.existsByCorreoUsuario("usuario@email.com")).thenReturn(false);

        // Simulamos que al guardar el usuario se devuelve un objeto con ID
        Usuario usuarioGuardado = new Usuario();
        usuarioGuardado.setIdUsuario(1);
        usuarioGuardado.setCorreoUsuario("usuario@email.com");

        // Cuando se llame a save(), devuelve el usuario guardado
        when(usuarioRepo.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        // Usamos el método a probar
        Usuario resultado = usuarioServ.agregar(datosCrear);

        // Verificamos que los datos sean correctos
        assertNotNull(resultado);
        assertEquals("usuario@email.com", resultado.getCorreoUsuario());

        // Verificamos que el método save fue llamado una vez
        verify(usuarioRepo, times(1)).save(any(Usuario.class));
    }

}
