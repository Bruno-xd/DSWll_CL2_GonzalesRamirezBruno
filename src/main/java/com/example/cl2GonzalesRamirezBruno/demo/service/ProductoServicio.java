package com.example.cl2GonzalesRamirezBruno.demo.service;

import com.example.cl2GonzalesRamirezBruno.demo.model.Producto;
import com.example.cl2GonzalesRamirezBruno.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    public void guardarProducto(Producto producto){
        productoRepository.save(producto);
    }

    public Producto obtenerProductoPorId(Integer id){
        return productoRepository.findById(id).get();
    }

    public void eliminarProducto(Integer id){
        productoRepository.deleteById(id);
    }

}
