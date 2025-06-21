package com.duoc.api_soporte.model.entities;

/*------------------------------------------*/

// Importaciones
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*------------------------------------------*/

@Data
@Entity
@Table(name = "provider")               // Nombre de la tabla
public class Proveedor {

    @Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    private Long idProveedor;                       // Identificador del proveedor

    @Column(nullable = false)
    private String nombreProveedor;                 // Nombre del proveedor

    @Column(nullable = false)
    private String rutProveedor;                    // Rut del proveedor

    @Column(nullable = false, unique = true)
    private String correoProveedor;                 // Correo el proveedor

    private String fonoProveedor;                   // Telefono del proveedor

    @Column(length = 1000)
    private String descripcionServicioProveedor;    // Descripcion del servicio que ofrece

    private boolean estadoProveedor;                // Estado del proveedor

}
