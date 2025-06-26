package com.duoc.api_resenas.creacion;

/*------------------------------------------------*/

// Importaciones
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_resenas.models.entities.Resenia;
import com.duoc.api_resenas.models.request.ReseniaCreate;
import com.duoc.api_resenas.repository.ReseniaRepository;
import com.duoc.api_resenas.service.ReseniaService;

/*------------------------------------------------*/

public class CreacionReseniaTest {

    // Simulamos (mockeamos) el repositorio
    @Mock
    private ReseniaRepository reseniaRepo;

    // Inyectamos el mock en la clase que queremos probar
    @InjectMocks
    private ReseniaService reseniaService;

    // Antes de cada prueba, activamos los mocks
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Prueba cuando los datos son válidos
    @Test
    void testCrearResenia() {
        // Creamos un objeto con datos de entrada válidos
        ReseniaCreate entrada = new ReseniaCreate();
        entrada.setPuntuacionResenia(5);
        entrada.setTextoResenia("Excelente curso");

        // Creamos un objeto simulado que será retornado por el repositorio
        Resenia reseniaGuardada = new Resenia();
        reseniaGuardada.setPuntuacionResenia(5);
        reseniaGuardada.setTextoResenia("Excelente curso");
        reseniaGuardada.setFechaResenia(new Date());

        // Simulamos el comportamiento del método save del repositorio
        when(reseniaRepo.save(any(Resenia.class))).thenReturn(reseniaGuardada);

        // Ejecutamos el método a probar
        Resenia resultado = reseniaService.dejarResena(entrada);

        // Comprobamos que el resultado no sea null y que los datos estén correctos
        assertNotNull(resultado);
        assertEquals(5, resultado.getPuntuacionResenia());
        assertEquals("Excelente curso", resultado.getTextoResenia());

        // Verificamos que el repositorio fue usado para guardar
        verify(reseniaRepo).save(any(Resenia.class));
    }


    // Prueba cuando la puntuación está fuera del rango permitido
    @Test
    void testErrorCrearResenia() {

        // Creamos una reseña con puntuación inválida
        ReseniaCreate entrada = new ReseniaCreate();
        // Recordar que el valor 6 está fuera del rango permitido
        entrada.setPuntuacionResenia(6); 

        // Comprobamos que el método lance una excepción por puntuación inválida
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            reseniaService.dejarResena(entrada);
        });

        // Validamos que el mensaje de error sea el esperado
        assertTrue(ex.getReason().contains("entre 1 y 5"));

        // Aseguramos que NO se llamó al método save del repositorio
        verify(reseniaRepo, never()).save(any(Resenia.class));
    }
}

