package com.example.demospringrest.repositories;

import com.example.demospringrest.entities.Factura;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name="facturas")
public class FacturaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Factura> buscarTodas(){
        return entityManager.createQuery("SELECT f FROM Factura f", Factura.class).getResultList();
    }
    public Factura buscarPorId(int numero){
        //return entityManager.createQuery("SELECT f FROM Factura f WHERE numero = " + numero, Factura.class).getSingleResult();
        return entityManager.find(Factura.class, numero);
    }

    @Transactional
    public void insertarFactura(Factura factura){
        entityManager.persist(factura);
    }

    @Transactional
    public void actualizarFactura(Factura factura){
        entityManager.merge(factura);
    }

    @Transactional
    public void borrarFactura(Factura factura){
        entityManager.remove(entityManager.merge(factura));
    };

}
