package com.example.cl2GonzalesRamirezBruno.demo.repository;

import com.example.cl2GonzalesRamirezBruno.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}