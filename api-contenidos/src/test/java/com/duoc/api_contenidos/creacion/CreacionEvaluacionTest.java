package com.duoc.api_contenidos.creacion;

/*------------------------------------------------*/

// Importaciones
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_contenidos.model.entity.Evaluacion;
import com.duoc.api_contenidos.model.request.EvaluacionCreate;
import com.duoc.api_contenidos.model.request.PreguntaCreate;
import com.duoc.api_contenidos.repository.EvaluacionRepository;
import com.duoc.api_contenidos.service.EvaluacionService;

/*------------------------------------------------*/

public class CreacionEvaluacionTest {
    
    // Se simula el repositorio para no usar base de datos real
    @Mock
    private EvaluacionRepository evaluacionRepo;

    // Se inyecta el mock en el servicio real que se va a probar
    @InjectMocks
    private EvaluacionService evaluacionServ;

    @BeforeEach
    void setUp() {
        // Se inicializan los mocks antes de cada prueba
        MockitoAnnotations.openMocks(this);
    }


    // Test para comprobar que la evaluacion se cree correctamente
    @Test
    void testCreacionEvaluacion() {

        // Creamos una instancia del DTO con datos válidos
        EvaluacionCreate datos = new EvaluacionCreate();
        datos.setTipoEvaluacion("Final");

        // Creamos una lista de preguntas simuladas
        List<PreguntaCreate> preguntas = new ArrayList<>();

        PreguntaCreate pregunta = new PreguntaCreate();
        pregunta.setEnunciadoPregunta("¿Qué es Spring Boot?");
        pregunta.setOpcionesPregunta("Framework | Lenguaje | IDE");
        pregunta.setRespCorrectaPregunta("Framework");

        preguntas.add(pregunta);

        // Asignamos la lista al objeto de entrada
        datos.setListaPreguntas(preguntas);

        // Simulamos el comportamiento del método save() para devolver lo mismo que recibe
        when(evaluacionRepo.save(any(Evaluacion.class))).thenAnswer(invoc -> invoc.getArgument(0));

        // Act = Ejecución del método que estamos probando
        Evaluacion resultado = evaluacionServ.agregar(datos);

        // Assert = Verificación de los resultados esperados
        assertNotNull(resultado); // Verificamos que no sea nulo
        assertEquals("Final", resultado.getTipoEvaluacion()); // Verificamos el tipo
        assertEquals(1, resultado.getListaPreguntas().size()); // Verificamos que haya una pregunta
        assertEquals("¿Qué es Spring Boot?", resultado.getListaPreguntas().get(0).getEnunciadoPregunta());

        // Verificamos que se llamó al método save una vez
        verify(evaluacionRepo, times(1)).save(any(Evaluacion.class));
    }

    // Test en caso de que haya algun fallo en la creacion de la evaluacion
    @Test
    void testErrorCreacionEvaluacion() {

        // Creamos el objeto de entrada
        EvaluacionCreate datos = new EvaluacionCreate();
        datos.setTipoEvaluacion("Diagnóstico");
        datos.setListaPreguntas(new ArrayList<>()); // lista vacía

        // Simulamos que ocurre un error al guardar
        when(evaluacionRepo.save(any())).thenThrow(new RuntimeException("Fallo en DB"));

        // Act & Assert = Verificamos que se lanza una excepción cuando hay error
        assertThrows(ResponseStatusException.class, () -> {
            evaluacionServ.agregar(datos);
        });

        // También podemos verificar que se intentó guardar una evaluación
        verify(evaluacionRepo, times(1)).save(any());
    }
}

