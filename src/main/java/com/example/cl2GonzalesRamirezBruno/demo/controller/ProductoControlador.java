package com.example.cl2GonzalesRamirezBruno.demo.controller;

import com.example.cl2GonzalesRamirezBruno.demo.model.Producto;
import com.example.cl2GonzalesRamirezBruno.demo.service.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductoControlador {

    @Autowired
    private ProductoServicio servicio;

    //--------------------------------------------------------------------------------------------
    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return servicio.listarProductos();
    }

    //--------------------------------------------------------------------------------------------
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Integer id) {
        try {
            Producto producto = servicio.obtenerProductoPorId(id);
            return new ResponseEntity<Producto>(producto, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
    }

    //--------------------------------------------------------------------------------------------
    @PostMapping("/productos")
    public void guardarProducto(@RequestBody Producto producto) {
        servicio.guardarProducto(producto);
    }

    //--------------------------------------------------------------------------------------------
    @PutMapping("productos/{id}")
    public ResponseEntity<?> actualizarProducto(@RequestBody Producto producto, @PathVariable Integer id) {
        try {
            Producto ProductoActual = servicio.obtenerProductoPorId(id);

            ProductoActual.setNombre(producto.getNombre());
            ProductoActual.setDescripcion(producto.getDescripcion());
            ProductoActual.setCantidad(producto.getCantidad());
            ProductoActual.setFechaVencimiento(producto.getFechaVencimiento());

            servicio.guardarProducto(ProductoActual);
            return new ResponseEntity<Producto>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
    }

    //--------------------------------------------------------------------------------------------
    @DeleteMapping("productos/{id}")
    public void eliminarProducto(@PathVariable Integer id) {
        servicio.eliminarProducto(id);
    }

}
