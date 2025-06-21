package com.duoc.api_soporte.model.request;

import jakarta.validation.constraints.Email;

/*------------------------------------------*/

// Importaciones
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*------------------------------------------*/

@Data
public class ProveedorCreate {

    @NotBlank
    private String nombreProveedor;                 // Nombre del proveedor

    @NotBlank
    private String rutProveedor;                    // Rut del proveedor

    @Email
    @NotBlank
    private String correoProveedor;                 // Correo el proveedor

    private String fonoProveedor;                   // Telefono del proveedor

    @NotBlank
    private String descripcionServicioProveedor;    // Descripcion del servicio que ofrece

}
