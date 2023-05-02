package com.iset.produit;

import com.iset.produit.entities.Produit;
import com.iset.produit.service.ProduitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ProduitApplication implements CommandLineRunner {
    @Autowired
    private ProduitServiceImpl service;
    public static void main(String[] args) {
        SpringApplication.run(ProduitApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {

    }

}
