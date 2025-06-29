package com.duoc.api_cursos.assemblers;

/*------------------------------------------*/

// Importaciones
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.duoc.api_cursos.model.entity.Curso;
import com.duoc.api_cursos.controller.CursoController;
import org.springframework.hateoas.Link;

/*------------------------------------------*/

/* Haciendo el objeto Curso con enlaces HATEOAS, para que 
devuelva los datos corresponsientes además de enlaces 
a recursos que estén relacionados */

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {

    @Override
    public EntityModel<Curso> toModel(Curso curso) {
        return EntityModel.of(curso,

            /* Todos los métodos que se encuentren aquí deben estar bien definidos
            además de que deben coincidir en su 'firma', es decir, que estén exactamente iguales en nombre 
            del método, tipo de datos, etc. Para que methodOn pueda ejecutarlos correctamente */ 

            // Self link
            linkTo(methodOn(CursoController.class).obtenerUno(curso.getIdCurso())).withSelfRel(),

            // Enlace al microservicio de contenidos relacionados a este curso
            Link.of("http://localhost:8082/contents/allByCourse/" + curso.getIdCurso()).withRel("contenido_curso"),

            // Enlace al microservicio de inscripciones del curso
            Link.of("http://localhost:8083/inscriptions/allByCourse/" + curso.getIdCurso()).withRel("inscripciones"),

            // Enlace al microservicio a todas las resenias
            Link.of("http://localhost:8086/reviews/all/" + curso.getIdCurso()).withRel("todas las reseñas")

        );
    }
}