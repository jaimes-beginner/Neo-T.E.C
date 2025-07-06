package com.duoc.api_soporte.repository;

/*------------------------------------------*/

// Importaciones
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.duoc.api_soporte.model.entities.Proveedor;

/*------------------------------------------*/

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{
   
}
