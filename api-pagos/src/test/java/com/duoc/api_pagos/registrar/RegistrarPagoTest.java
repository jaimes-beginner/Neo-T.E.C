package com.duoc.api_pagos.registrar;

/*------------------------------------------------*/

// Importaciones
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_pagos.model.entity.Pago;
import com.duoc.api_pagos.model.request.PagoCreate;
import com.duoc.api_pagos.repository.PagoRepository;
import com.duoc.api_pagos.service.PagoService;
import reactor.core.publisher.Mono;

/*------------------------------------------------*/

public class RegistrarPagoTest {

    // Simulamos (mockeamos) el repositorio
    @Mock
    private PagoRepository pagoRepo;

    // Inyectamos el mock en la clase que queremos probar
    @Mock
    private WebClient usuarioWebClient;

    // Antes de cada prueba, activamos los mocks
    @Mock
    private WebClient cursoWebClient;

    @InjectMocks
    private PagoService pagoServ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Mocks internos para el WebClient 
    @Mock private WebClient.RequestHeadersUriSpec requestUsuario;
    @Mock private WebClient.RequestHeadersSpec requestUsuarioSpec;
    @Mock private WebClient.ResponseSpec responseUsuario;
    @Mock private WebClient.RequestHeadersUriSpec requestCurso;
    @Mock private WebClient.RequestHeadersSpec requestCursoSpec;
    @Mock private WebClient.ResponseSpec responseCurso;


    // Test para ver si los datos están en orden para registrar un pago
    @Test
    void testRegistrarPagos() {

        // Preparar datos de entrada
        PagoCreate datos = new PagoCreate();
        datos.setIdUsuarioPago(1);
        datos.setIdCursoPago(10);
        datos.setMontoPago(50000.00);

        Pago pagoSimulado = new Pago();
        pagoSimulado.setIdPago(100);
        pagoSimulado.setFechaPago(new Date());
        pagoSimulado.setIdUsuarioPago(1);
        pagoSimulado.setIdCursoPago(10);
        pagoSimulado.setMontoPago(50000.00);

        // Simular una llamada al microservicio de usuario
        when(usuarioWebClient.get()).thenReturn(requestUsuario);
        when(requestUsuario.uri("/users/1")).thenReturn(requestUsuario);
        when(requestUsuario.retrieve()).thenReturn(responseUsuario);
        when(responseUsuario.toBodilessEntity()).thenReturn(Mono.just(ResponseEntity.ok().build()));

        when(cursoWebClient.get()).thenReturn(requestCurso);
        when(requestCurso.uri("/courses/10")).thenReturn(requestCurso);
        when(requestCurso.retrieve()).thenReturn(responseCurso);
        when(responseCurso.toBodilessEntity()).thenReturn(Mono.just(ResponseEntity.ok().build()));

        // Simular guardado en base de datos
        when(pagoRepo.save(any(Pago.class))).thenReturn(pagoSimulado);

        // Ejecutar el método que queremos probar
        Pago resultado = pagoServ.registrarPago(datos);

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals(50000, resultado.getMontoPago());
        assertEquals(1L, resultado.getIdUsuarioPago());
        assertEquals(10L, resultado.getIdCursoPago());
        verify(pagoRepo).save(any(Pago.class)); // Verifica que se guardó
    }


    // Test para comprobar que pasaría si algún datos falla/no fue bien ingresado
    @Test
    void testErrorRegistrarPagos() {

        // Preparar los datos de entrada
        PagoCreate datos = new PagoCreate();
        datos.setIdUsuarioPago(1);
        datos.setIdCursoPago(10);
        datos.setMontoPago(50000.00);

        // Simular fallo al llamar al microservicio de usuario
        when(usuarioWebClient.get()).thenReturn(requestUsuario);
        when(requestUsuario.uri("/users/1")).thenReturn(requestUsuario);
        when(requestUsuario.retrieve()).thenThrow(new RuntimeException("Usuario no encontrado"));

        // Verificar el resultado
        assertThrows(ResponseStatusException.class, () -> {
            pagoServ.registrarPago(datos);
        });

        // No se debe intentar guardar si falla antes
        verify(pagoRepo, never()).save(any());
    }
}
