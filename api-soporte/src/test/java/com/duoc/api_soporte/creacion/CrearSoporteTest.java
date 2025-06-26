package com.duoc.api_soporte.creacion;

/*------------------------------------------------*/

// Importaciones
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_soporte.model.entities.SoporteTicket;
import com.duoc.api_soporte.model.request.SoporteTicketCreate;
import com.duoc.api_soporte.repository.SoporteTicketRepository;
import com.duoc.api_soporte.service.SoporteTicketService;

/*------------------------------------------------*/

public class CrearSoporteTest {

    @Mock
    private SoporteTicketRepository soporteRepo;

    @InjectMocks
    private SoporteTicketService soporteServ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test en donde se valida que todo funcione correctamente
    @Test
    void testCrearTicket() {

        // Simular datos de entrada
        SoporteTicketCreate datos = new SoporteTicketCreate();
        datos.setIncidenteTicket("Error al iniciar sesión");
        datos.setEstadoTicket("Abierto");

        SoporteTicket ticketSimulado = new SoporteTicket();
        ticketSimulado.setIdTicket(1);
        ticketSimulado.setTemaTicket("Error al iniciar sesión");
        ticketSimulado.setEstadoTicket("Abierto");
        ticketSimulado.setFechaTicket(new Date());

        // Simula comportamiento del repositorio
        when(soporteRepo.save(any(SoporteTicket.class))).thenReturn(ticketSimulado);

        // Ejecutar el método del servicio
        SoporteTicket resultado = soporteServ.crearTicket(datos);

        // Verificar resultado
        assertNotNull(resultado);
        assertEquals("Error al iniciar sesión", resultado.getTemaTicket());
        assertEquals("Abierto", resultado.getEstadoTicket());
        verify(soporteRepo).save(any(SoporteTicket.class)); // Verifica que se llamó al repo
    }

    // Test para comprobar que debería mostrar si hay algún fallo con los datos
    @Test
    void testErrorCrearTicket() {
        // Arrange
        SoporteTicketCreate datos = new SoporteTicketCreate();
        datos.setIncidenteTicket("Error interno");
        datos.setEstadoTicket("Abierto");

        // Simula que el repositorio lanza una excepción
        when(soporteRepo.save(any(SoporteTicket.class))).thenThrow(new RuntimeException("Falla"));

        assertThrows(ResponseStatusException.class, () -> {
            soporteServ.crearTicket(datos);
        });

        verify(soporteRepo).save(any(SoporteTicket.class));
    }
}

