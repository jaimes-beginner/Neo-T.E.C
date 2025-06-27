package com.duoc.api_contenidos.creacion;

/*------------------------------------------------*/

// Importaciones
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_contenidos.model.entity.Contenido;
import com.duoc.api_contenidos.model.request.ContenidoCreate;
import com.duoc.api_contenidos.repository.ContenidoRepository;
import com.duoc.api_contenidos.service.ContenidoService;
import reactor.core.publisher.Mono;

/*------------------------------------------------*/

@ExtendWith(MockitoExtension.class)
public class CreacionContenidoTest {

    // Simulamos el repositorio de contenido, no accede a la base de datos real
    @Mock
    private ContenidoRepository contenidoRepo;

    // Simulamos el WebClient que hace la consulta al microservicio de cursos
    @Mock
    private WebClient cursosWebClient;

    // Simulamos las partes internas del WebClient (flujo en cadena)
    @Mock
    private WebClient.RequestHeadersUriSpec requestSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    // Inyectamos los mocks en el servicio real que vamos a probar
    @InjectMocks
    private ContenidoService contenidoService;


    // Esta prueba verifica que se guarda un contenido si el curso existe y funcione
    @Test
    void testCreacionContenido() {

        // Creamos un objeto de entrada 
        ContenidoCreate datos = new ContenidoCreate();
        datos.setIdCursoContenido(100);  // ID del curso existente
        datos.setTipoContenido("video");
        datos.setTituloContenido("Introducción a Spring Boot");
        datos.setUrlContenido("https://videos.edu/spring");

        // Simulamos el WebClient para validar que el curso existe
        when(cursosWebClient.get()).thenReturn(requestSpec); // llamada GET
        when(requestSpec.uri("/courses/" + datos.getIdCursoContenido())).thenReturn(requestSpec); // URL
        when(requestSpec.retrieve()).thenReturn(responseSpec); // Obtener respuesta
        when(responseSpec.toBodilessEntity()).thenReturn(Mono.just(ResponseEntity.ok().build())); // Respuesta 200 OK

        // Creamos el contenido simulado que sería guardado
        Contenido contenidoGuardado = new Contenido();
        contenidoGuardado.setIdCursoContenido(datos.getIdCursoContenido());
        contenidoGuardado.setTipoContenido(datos.getTipoContenido());
        contenidoGuardado.setTituloContenido(datos.getTituloContenido());
        contenidoGuardado.setUrlContenido(datos.getUrlContenido());

        // Configuramos que el repositorio devuelva ese contenido al guardar
        when(contenidoRepo.save(any(Contenido.class))).thenReturn(contenidoGuardado);

        // Usamos el método real que queremos probar
        Contenido resultado = contenidoService.agregar(datos);

        // Verificamos que el contenido fue guardado correctamente
        assertNotNull(resultado);  // Asegura que no sea null
        assertEquals("video", resultado.getTipoContenido()); // Verifica tipo
        assertEquals("Introducción a Spring Boot", resultado.getTituloContenido()); // Verifica título
        verify(contenidoRepo, times(1)).save(any()); // Verifica que se guardó una vez
    }


    // Test que verifica que si el curso no existe, se lanza una excepción
    @Test
    void testErrorCreacionContenido() {

        // Datos de entrada
        ContenidoCreate datos = new ContenidoCreate();
        datos.setIdCursoContenido(999); // Curso que no existe
        datos.setTipoContenido("video");
        datos.setTituloContenido("Clase fallida");
        datos.setUrlContenido("https://videos.edu/error");

        // Simulamos que el WebClient lanza un error al intentar obtener el curso
        when(cursosWebClient.get()).thenReturn(requestSpec);
        when(requestSpec.uri("/courses/" + datos.getIdCursoContenido())).thenReturn(requestSpec);
        when(requestSpec.retrieve()).thenReturn(responseSpec);

    
        // Aquí simulamos que cuando se hace `.block()`, lanza una excepción como si fuera 404
        when(responseSpec.toBodilessEntity()).thenThrow(new WebClientResponseException(
            404, "Not Found", null, null, null));


        // Esperamos que se lance una excepción cuando se llame al método
        assertThrows(ResponseStatusException.class, () -> {
            contenidoService.agregar(datos);
        });

        // Verificamos que no se llamó a guardar contenido
        verify(contenidoRepo, never()).save(any());
    }
}

