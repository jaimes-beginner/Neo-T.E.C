package com.duoc.api_cursos.creacion;

/*------------------------------------------------*/

// Importaciones
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.duoc.api_cursos.model.entity.Curso;
import com.duoc.api_cursos.model.request.CursoCreate;
import com.duoc.api_cursos.repository.CursoRepository;
import com.duoc.api_cursos.service.CursoService;

/*------------------------------------------------*/

public class CreacionCursoTest {

    // Simulamos el repositorio de inscripciones
    @Mock
    private CursoRepository cursoRepo;

    // Simulamos los servicios de inscripciones
    @InjectMocks
    private CursoService cursoServ;
    
    @BeforeEach
    void setUp() {
        // Se inicializan los mocks antes de cada prueba
        MockitoAnnotations.openMocks(this);
    }


    // Test para comprobar que si se cree un curso correctamente
    @Test
    void testCreacionCurso() {

        // Creamos un objeto de entrada simulando que llega con datos válidos
        CursoCreate datos = new CursoCreate();
        datos.setTituloCurso("Curso de Java");
        datos.setDescripcionCurso("Aprende Java desde cero");
        datos.setCategoriaCurso("Programación");
        datos.setIdInstructorCurso(1); // ID del instructor que dicta el curso

        // Creamos un objeto Curso que representa lo que esperamos que el repositorio guarde y devuelva
        Curso cursoGuardado = new Curso();
        cursoGuardado.setTituloCurso(datos.getTituloCurso());  // Copiamos los mismos datos
        cursoGuardado.setDescripcionCurso(datos.getDescripcionCurso());
        cursoGuardado.setCategoriaCurso(datos.getCategoriaCurso());
        cursoGuardado.setIdIntructorCurso(datos.getIdInstructorCurso());
        cursoGuardado.setEstadoCurso("En borrador");  // Este campo lo define el servicio
        cursoGuardado.setCreacionCurso(new Date());   // Fecha simulada (real)

        // Simulamos que cuando el repositorio recibe un curso, lo devuelve como si se hubiera guardado
        when(cursoRepo.save(any(Curso.class))).thenReturn(cursoGuardado);


        // Ejecutamos el método real del servicio que estamos probando
        Curso resultado = cursoServ.agregar(datos);


        // Verificamos que no sea nulo el curso que devuelve el servicio
        assertNotNull(resultado);

        // Verificamos que el título que tiene es el mismo que se esperaba
        assertEquals("Curso de Java", resultado.getTituloCurso());

        // Verificamos que el estado del curso haya sido asignado como "En borrador"
        assertEquals("En borrador", resultado.getEstadoCurso());

        // Verificamos que el método save() del repositorio se haya llamado exactamente una vez
        verify(cursoRepo, times(1)).save(any());
    }


    // Test para comprobar que un curso se publica correctamente
    @Test
    void testPublicarCurso() {
        
        int idCurso = 5;

        // Curso simulado que ya existe
        Curso cursoExistente = new Curso();
        cursoExistente.setIdCurso(idCurso);
        cursoExistente.setEstadoCurso("En borrador");

        // Simulamos que el servicio `obtenerUno(id)` devuelve un curso
        when(cursoRepo.findById(idCurso)).thenReturn(Optional.of(cursoExistente));
        when(cursoRepo.save(any(Curso.class))).thenAnswer(invoc -> invoc.getArgument(0));

        // Usamos el servicio
        Curso resultado = cursoServ.publicarUnCurso(idCurso);

        // Verificamos datos
        assertNotNull(resultado);
        assertEquals("Publicado", resultado.getEstadoCurso());
        verify(cursoRepo).save(cursoExistente);
    }
}
