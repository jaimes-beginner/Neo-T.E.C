package com.duoc.api_soporte.model.request;

import jakarta.validation.constraints.Email;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*------------------------------------------*/

@Data
public class ProveedorCreate {

    // NOMBRE PROVEEDOR: Nombre del proveedor
    @NotBlank
    private String nombreProveedor;                

    // RUT PROVEEDOR: Rut del proveedor
    @NotBlank
    private String rutProveedor;                  

    // CORREO PROVEEDOR: Correo el proveedor
    @Email
    @NotBlank
    private String correoProveedor;   

    // FONO PROVEEDOR: Telefono del proveedor
    private String fonoProveedor;                

    // DESCRIPCION SERVICIO PROVEEDOR: Descripcion del servicio que ofrece
    @NotBlank
    private String descripcionServicioProveedor;    

}
