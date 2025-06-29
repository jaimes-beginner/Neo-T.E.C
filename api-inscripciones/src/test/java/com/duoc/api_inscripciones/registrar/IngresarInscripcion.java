package com.duoc.api_inscripciones.registrar;

/*------------------------------------------------*/

// Importaciones
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_inscripciones.model.entity.Inscripcion;
import com.duoc.api_inscripciones.model.request.InscripcionCreate;
import com.duoc.api_inscripciones.repository.InscripcionRepository;
import com.duoc.api_inscripciones.service.InscripcionService;

/*------------------------------------------------*/

public class IngresarInscripcion {


    // Simulamos el repositorio de inscripciones
    @Mock
    private InscripcionRepository inscripcionRepo;

    // Inyectamos el mock en el servicio que vamos a probar
    @Spy
    @InjectMocks
    private InscripcionService inscripcionServ;

    @BeforeEach
    void setUp() {
        // Activamos los mocks
        MockitoAnnotations.openMocks(this);
    }

    // Test para asegurarse de que todos los datos-conexiones estén bien implementadas
    @Test
    void testRegistrarInscripcion() {
        // Arrange
        int idUsuario = 1;
        int idCurso = 10;

        // Creamos el DTO con los datos necesarios
        InscripcionCreate datosCrear = new InscripcionCreate();
        datosCrear.setIdUsuarioInscripcion(idUsuario);
        datosCrear.setIdCursoInscripcion(idCurso);

        // Simulamos que NO existe una inscripción previa
        when(inscripcionRepo.existsByIdUsuarioInscripcionAndIdCursoInscripcion(idUsuario, idCurso)).thenReturn(false);

        // Simulamos que el usuario y curso existen
        doReturn(true).when(inscripcionServ).existenciaUsuario(idUsuario);
        doReturn(true).when(inscripcionServ).existenciaCurso(idCurso);

        // Simulamos el guardado del objeto Inscripcion
        Inscripcion inscripcionGuardada = new Inscripcion();
        inscripcionGuardada.setIdUsuarioInscripcion(idUsuario);
        inscripcionGuardada.setIdCursoInscripcion(idCurso);
        inscripcionGuardada.setFechaInscripcion(new Date());
        inscripcionGuardada.setEstadoInscripcion(true);

        when(inscripcionRepo.save(any(Inscripcion.class))).thenReturn(inscripcionGuardada);

        // Act
        Inscripcion resultado = inscripcionServ.agregar(datosCrear);

        // Assert
        assertNotNull(resultado);
        assertEquals(idUsuario, resultado.getIdUsuarioInscripcion());
        assertEquals(idCurso, resultado.getIdCursoInscripcion());
        assertTrue(resultado.getEstadoInscripcion());

        verify(inscripcionRepo).save(any(Inscripcion.class));
    }

    // Test para comprobar que va a ocurrir en caso de que falle la inscripcion
    @Test
    void testErrorRegistrarInscripcion() {

        // Arrange
        int idUsuario = 1;
        int idCurso = 10;

        // Creamos el DTO con los datos necesarios
        InscripcionCreate datosCrear = new InscripcionCreate();
        datosCrear.setIdUsuarioInscripcion(idUsuario);
        datosCrear.setIdCursoInscripcion(idCurso);

        // Simulamos que ya hay una inscripción existente
        when(inscripcionRepo.existsByIdUsuarioInscripcionAndIdCursoInscripcion(idUsuario, idCurso)).thenReturn(true);

        // Verificamos los no-datos
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            inscripcionServ.agregar(datosCrear);
        });

        assertEquals("400 BAD_REQUEST \"Ya estás inscrito\"", ex.getMessage());

        // Aseguramos que NO se haya guardado nada por la falla
        verify(inscripcionRepo, never()).save(any());
    }

    
}

