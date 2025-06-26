package com.duoc.api_soporte.creacion;

/*------------------------------------------------*/

// Importaciones
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_soporte.model.entities.Proveedor;
import com.duoc.api_soporte.model.request.ProveedorCreate;
import com.duoc.api_soporte.repository.ProveedorRepository;
import com.duoc.api_soporte.service.ProveedorService;

/*------------------------------------------------*/

public class CrearProveedorService {

    @Mock
    private ProveedorRepository proveedorRepo;

    @InjectMocks
    private ProveedorService proveedorServ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Teste de creación de un proveedor, validando que todos los datos esten correctos
    @Test
    void testCrearProveedor() {

        // Simular datos de entrada
        ProveedorCreate datos = new ProveedorCreate();
        datos.setNombreProveedor("Proveedor 1");
        datos.setRutProveedor("12345678-9");
        datos.setDescripcionServicioProveedor("Servicios de computación");
        datos.setCorreoProveedor("correo@proveedor.com");
        datos.setFonoProveedor("987654321");

        Proveedor proveedorSimulado = new Proveedor();
        proveedorSimulado.setIdProveedor(1);
        proveedorSimulado.setEstadoProveedor(true);
        proveedorSimulado.setNombreProveedor("Proveedor 1");
        proveedorSimulado.setRutProveedor("12345678-9");
        proveedorSimulado.setDescripcionServicioProveedor("Servicios de computación");
        proveedorSimulado.setCorreoProveedor("correo@proveedor.com");
        proveedorSimulado.setFonoProveedor("987654321");

        // Simula el comportamiento del repositorio
        when(proveedorRepo.save(any(Proveedor.class))).thenReturn(proveedorSimulado);

        // Ejecutar el método del servicio
        Proveedor resultado = proveedorServ.agregar(datos);

        // Verficar los resultados
        assertNotNull(resultado);
        assertEquals("Proveedor 1", resultado.getNombreProveedor());
        assertEquals("correo@proveedor.com", resultado.getCorreoProveedor());
        assertTrue(resultado.getEstadoProveedor());
        verify(proveedorRepo).save(any(Proveedor.class));
    }

    // Test para comprobar que debería mostrar si hay algún fallo con los datos
    @Test
    void testErrorCrearProveedor() {
        // Arrange
        ProveedorCreate datos = new ProveedorCreate();
        datos.setNombreProveedor("Proveedor con error");

        when(proveedorRepo.save(any(Proveedor.class))).thenThrow(new RuntimeException("Error"));

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> {
            proveedorServ.agregar(datos);
        });

        verify(proveedorRepo).save(any(Proveedor.class));
    }
}

