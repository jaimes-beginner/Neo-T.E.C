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
@Table(name = "roles")          // Nombre de la tabla
public class Rol {

    @Id
    @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    private int idRol;                      // Identificador del rol

    @Column(nullable = false)
    private String nombreRol;               // Nombre del rol

}
