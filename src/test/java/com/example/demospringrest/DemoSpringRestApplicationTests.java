package com.example.demospringrest;

import com.example.demospringrest.entities.Factura;
import com.example.demospringrest.repositories.FacturaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Sql(scripts = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class DemoSpringRestApplicationTests {

    @Autowired
    FacturaRepository facturaRepository;

    @Test
    void buscarTodasTest() {

        List<Factura> listaFacturas = facturaRepository.buscarTodas();
        assertThat(listaFacturas, hasItem(new Factura(1)));
    }

    @Test
    void buscarTodasRestTest() {
        get("/facturas").then().body("numero", hasItem(1));
    }

    @Test
    void buscarFacturaPorId() {
        get("/facturas/1").then().body("numero", equalTo(1));
    }

    @Test
    void borrarFacturaTest() {

        given().pathParam("numero", 1)
                .delete("/facturas/{numero}")
                .then()
                .statusCode(200);
    }

    @Test
    void insertFacturaTest() throws JsonProcessingException {

        Factura factura = new Factura(4, "Auriculares", 50);

        ObjectMapper mapper = new ObjectMapper();
        String facturaJson = mapper.writeValueAsString(factura);

        given()
                .header("Content-type", "application/json")
                .and().body(facturaJson)
                .post("/facturas").then().statusCode(200);

        //get("/facturas/3").then().body("numero", equalTo(3));
    }

    @Test
    void actualizarFacturaTest() throws JsonProcessingException {

        Factura factura = new Factura(2, "Mouse", 30);

        ObjectMapper mapper = new ObjectMapper();
        String facturaJson = mapper.writeValueAsString(factura);

        given()
                .header("Content-type", "application/json")
                .and().body(facturaJson)
                .pathParam("numero", 2)
                .put("/facturas/{numero}").then().statusCode(200);
    }
}
