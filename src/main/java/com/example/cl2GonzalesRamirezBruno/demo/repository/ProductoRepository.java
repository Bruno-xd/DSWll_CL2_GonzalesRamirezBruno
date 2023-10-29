package com.example.cl2GonzalesRamirezBruno.demo.repository;

import com.example.cl2GonzalesRamirezBruno.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    public Producto findByNombre(String nombre);

    @Query("SELECT p FROM Producto p WHERE p.cantidad > 10 AND p.cantidad < 100")
    List<Producto> findProductosEntre10y100();

    @Query(value = "SELECT * FROM Producto p WHERE YEAR(p.fecha_vencimiento) = 2024", nativeQuery = true)
    List<Producto> findProductosConVencimiento2024();
}
