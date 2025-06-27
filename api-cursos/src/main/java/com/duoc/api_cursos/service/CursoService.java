package com.duoc.api_cursos.service;

/*------------------------------------------*/

// Importaciones
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_cursos.model.entity.Curso;
import com.duoc.api_cursos.model.request.CursoCreate;
import com.duoc.api_cursos.model.request.CursoUpdate;
import com.duoc.api_cursos.repository.CursoRepository;

/*------------------------------------------*/

@Service
public class CursoService {
    
    // Atributos
    @Autowired
    private CursoRepository cursoRepo;

    // Obtener todos los cursos
    public List<Curso> obtenerTodos() {
        return cursoRepo.findAll();
    }

    // Obtener un curso por su id 
    public Curso obtenerUno(int id) {
        return cursoRepo.findById(id).orElse(null);
    }

    // Considerar asociar a un profesor en la clase...
    // Agregar un curso
    public Curso agregar(CursoCreate datosCrear) {
        Curso curso = new Curso();
        try{
            curso.setEstadoCurso("En borrador");
            curso.setCreacionCurso(new Date());
            curso.setTituloCurso(datosCrear.getTituloCurso());
            curso.setDescripcionCurso(datosCrear.getDescripcionCurso());
            curso.setCategoriaCurso(datosCrear.getCategoriaCurso());
            curso.setIdIntructorCurso(datosCrear.getIdInstructorCurso());
            return cursoRepo.save(curso);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    // Publicar un curso
    public Curso publicarUnCurso(int id) {
        Curso cursoPublicar = obtenerUno(id);
        if(cursoPublicar == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        cursoPublicar.setEstadoCurso("Publicado");
        return cursoRepo.save(cursoPublicar);
    }   

    // Eliminar un curso por su id
    public void eliminar(int id) {
        Curso curso = obtenerUno(id);
        if(curso == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        cursoRepo.delete(curso);
    }

    // Modificar un curso por su id (datosModificar)
    public Curso modificar(CursoUpdate datosModificar) {
        Curso curso = obtenerUno(datosModificar.getIdCurso());
        if(curso == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(datosModificar.getTituloCurso() != null) {
            curso.setTituloCurso(datosModificar.getTituloCurso());
        }
        if(datosModificar.getDescripcionCurso() != null) {
            curso.setDescripcionCurso(datosModificar.getDescripcionCurso());
        }
        if(datosModificar.getCategoriaCurso() != null) {
            curso.setCategoriaCurso(datosModificar.getCategoriaCurso());
        }
        if(datosModificar.getIdInstructorCurso() != null) {
            curso.setIdIntructorCurso(datosModificar.getIdInstructorCurso());
        }
        return cursoRepo.save(curso);
    }

}
