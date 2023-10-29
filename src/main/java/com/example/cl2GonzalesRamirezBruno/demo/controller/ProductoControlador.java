package com.example.cl2GonzalesRamirezBruno.demo.controller;

import com.example.cl2GonzalesRamirezBruno.demo.model.Producto;
import com.example.cl2GonzalesRamirezBruno.demo.repository.ProductoRepository;
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

    @Autowired
    private ProductoRepository repository;

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
            ProductoActual.setFecha_vencimiento(producto.getFecha_vencimiento());

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

    //--------------------------------------------------------------------------------------------
    @GetMapping("/obtenerProducto/{nombre}")
    public ResponseEntity<Producto> obtenerProductoPorNombre(@PathVariable String nombre) {
        Producto producto = servicio.obtenerProductoPorNombre(nombre);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //--------------------------------------------------------------------------------------------
    @GetMapping("/productosEntre10y100")
    public List<Producto> buscarProductosEntre10y100() {
        return repository.findProductosEntre10y100();
    }

    //--------------------------------------------------------------------------------------------
    @GetMapping("/productosConVencimiento2024")
    public List<Producto> buscarProductosConVencimiento2024() {
        return repository.findProductosConVencimiento2024();
    }

    //commit de lo que sea XD

}
