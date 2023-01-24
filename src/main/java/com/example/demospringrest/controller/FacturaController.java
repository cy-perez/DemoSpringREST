package com.example.demospringrest.controller;

import com.example.demospringrest.entities.Factura;
import com.example.demospringrest.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facturas")
public class FacturaController {
    @Autowired
    FacturaRepository facturaRepository;

    @GetMapping
    public List<Factura> buscarTodas(){
        return facturaRepository.buscarTodas();
    }

    @GetMapping("/{numero}")
    public Factura buscarPorId(@PathVariable("numero") int numero){
        return facturaRepository.buscarPorId(numero);
    }

    @PostMapping
    public void insertarFactura(@RequestBody Factura factura){
        facturaRepository.insertarFactura(factura);
    }

    @PutMapping("/{numero}")
    public void actualizarFactura(@RequestBody Factura factura){
        facturaRepository.actualizarFactura(factura);
    }

    @DeleteMapping("/{numero}")
    public void borrarFactura(Factura factura){
        facturaRepository.borrarFactura(factura);
    }
}
