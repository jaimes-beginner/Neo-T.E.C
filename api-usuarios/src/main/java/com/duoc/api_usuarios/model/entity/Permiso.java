package com.duoc.api_usuarios.model.entity;

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
@Table(name = "permisos")       // Nombre de la tabla
public class Permiso {

    @Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    private int idPermiso;                  // Identificador del permiso

    @Column(nullable = false)
    private String nombrePermiso;           // Nombre del permiso

}
