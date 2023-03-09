package com.aurorastudio.demo.controllers;

import com.aurorastudio.demo.entities.Supplier;
import com.aurorastudio.demo.exceptions.SupplierDataViolationException;
import com.aurorastudio.demo.exceptions.SupplierNotFoundException;
import com.aurorastudio.demo.exceptions.SupplierNullException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SupplierControllerTest {
    @Autowired
    SupplierController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void whenCreateSupplier_thenAssertIDNotNull(){
        Supplier supplier = controller.createSupplier(new Supplier("Empresa01", "China"));
        assertThat(supplier.getId()).isNotNull();
    }

    @Test
    public void whenCreateSupplierNull_thenTrhowSupplierNullExceptionError(){
        SupplierNullException e = Assertions.assertThrows(SupplierNullException.class, () ->{
            controller.createSupplier(null);
        });
        Assertions.assertEquals("El supplier no puede ser null", e.getMessage());
    }

    @Test
    public void whenCreateSupplierNameNull_thenTrhowSupplierDataViolationExceptionError(){
        SupplierDataViolationException e = Assertions.assertThrows(SupplierDataViolationException.class, () ->{
            controller.createSupplier(new Supplier(null, "China"));
        });
        Assertions.assertEquals("El campo: name, no puede ser null o vacío", e.getMessage());
    }

    @Test
    public void whenCreateSupplierCountryNull_thenTrhowSupplierDataViolationExceptionError(){
        SupplierDataViolationException e = Assertions.assertThrows(SupplierDataViolationException.class, () ->{
            controller.createSupplier(new Supplier("Supplier01", null));
        });
        Assertions.assertEquals("El campo: country, no puede ser null o vacío", e.getMessage());
    }

    @Test
    public void whenCreateSupplierNameEmpty_thenTrhowSupplierDataViolationExceptionError(){
        SupplierDataViolationException e = Assertions.assertThrows(SupplierDataViolationException.class, () ->{
            controller.createSupplier(new Supplier("", "China"));
        });
        Assertions.assertEquals("El campo: name, no puede ser null o vacío", e.getMessage());
    }

    @Test
    public void whenCreateSupplierCountryEmpty_thenTrhowSupplierDataViolationExceptionError(){
        SupplierDataViolationException e = Assertions.assertThrows(SupplierDataViolationException.class, () ->{
            controller.createSupplier(new Supplier("Supplier01", ""));
        });
        Assertions.assertEquals("El campo: country, no puede ser null o vacío", e.getMessage());
    }

    @Test
    public void whenUpdateNameSupplier_thenAssertChangeName(){
        Supplier supplier01 = controller.createSupplier(new Supplier("Empresa01", "China"));
        supplier01.setName("EmpresaTest");
        supplier01 = controller.updateSupplier(supplier01, supplier01.getId());
        assertThat(supplier01.getName()).isEqualTo("EmpresaTest");
    }
    @Test
    public void whenUpdateCountrySupplier_thenAssertChangeName(){
        Supplier supplier01 = controller.createSupplier(new Supplier("Empresa01", "China"));
        supplier01.setCountry("Francia");
        supplier01 = controller.updateSupplier(supplier01, supplier01.getId());
        assertThat(supplier01.getCountry()).isEqualTo("Francia");
    }
    @Test
    public void whenUpdateNullSupplier_thenThrowSupplierNullExceptionError(){
        final Supplier supplier = controller.createSupplier(new Supplier("Empresa01", "China"));

        SupplierNullException e = Assertions.assertThrows(SupplierNullException.class, ()->{
            controller.updateSupplier(null, supplier.getId());
        });

        Assertions.assertEquals("El supplier no puede ser null", e.getMessage());
    }

    @Test
    public void whenUpdateIDNull_thenThrowSupplierDataViolationExceptionError(){
        final Supplier supplier = controller.createSupplier(new Supplier("Empresa01", "China"));

        SupplierDataViolationException e = Assertions.assertThrows(SupplierDataViolationException.class, ()->{
            controller.updateSupplier(new Supplier("EmpresaTest", "Francia"), null);
        });

        Assertions.assertEquals("El campo: id, no puede ser null o vacío", e.getMessage());
    }

    @Test
    public void whenUpdateIDWrong_thenThrowSupplierNotFoundExceptionError(){
        final Supplier supplier = controller.createSupplier(new Supplier("Empresa01", "China"));

        SupplierNotFoundException e = Assertions.assertThrows(SupplierNotFoundException.class, ()->{
            controller.updateSupplier(new Supplier("EmpresaTest", "Francia"), 25l);
        });

        Assertions.assertEquals("Could not find supplier: 25", e.getMessage());
    }


}
