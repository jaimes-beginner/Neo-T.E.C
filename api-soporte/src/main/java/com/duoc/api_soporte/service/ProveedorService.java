package com.duoc.api_soporte.service;

/*------------------------------------------*/

// Importaciones
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.duoc.api_soporte.model.entities.Proveedor;
import com.duoc.api_soporte.model.request.ProveedorCreate;
import com.duoc.api_soporte.repository.ProveedorRepository;

/*------------------------------------------*/

@Service
public class ProveedorService {
    
    // Atributos
    @Autowired
    private ProveedorRepository proveedorRepo;

    // Obtener todos los proveedores
    public List<Proveedor> obtenerTodos() {
        return proveedorRepo.findAll();
    }

    // Obtener uno por su id
    public Proveedor obtenerUno(int idProveedor) {
        return proveedorRepo.findById(idProveedor).orElse(null);
    }

    // Agregar un proveedor
    public Proveedor agregar(ProveedorCreate datosCrear) {
        Proveedor proveedor = new Proveedor();
        try {
            proveedor.setEstadoProveedor(true);
            proveedor.setNombreProveedor(datosCrear.getNombreProveedor());
            proveedor.setRutProveedor(datosCrear.getRutProveedor());
            proveedor.setDescripcionServicioProveedor(datosCrear.getDescripcionServicioProveedor());
            proveedor.setCorreoProveedor(datosCrear.getCorreoProveedor());
            proveedor.setFonoProveedor(datosCrear.getFonoProveedor());
            return proveedorRepo.save(proveedor);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    // Eliminar un proveedor por su id
    public void eliminar(int idProveedor) {
        Proveedor proveedorEliminar = obtenerUno(idProveedor);
        if(proveedorEliminar == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        proveedorRepo.delete(proveedorEliminar);
    }

}
