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
@Table(name = "provider")             
public class Proveedor {

    // ID PROVEEDOR: Identificador del proveedor
    @Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    private int idProveedor;                      

    // NOMBRE PROVEEDOR: Nombre del proveedor
    @Column(nullable = false)
    private String nombreProveedor;              

    // RUT PROVEEDOR: Rut del proveedor
    @Column(nullable = false)
    private String rutProveedor;                   

    // CORREO PROVEEDOR: Correo el proveedor
    @Column(nullable = false, unique = true)
    private String correoProveedor;                

    // FONO PROVEEDOR: Telefono del proveedor
    private String fonoProveedor;                 

    // DESCRIPCION SERVICIO PROVEEDOR: Descripcion del servicio que ofrece
    @Column(length = 1000)
    private String descripcionServicioProveedor;   

    // ESTADO PROVEEDOR: Estado del proveedor
    private Boolean estadoProveedor;                

}
