package com.javPOL.magazineJava.config;

import com.javPOL.magazineJava.dto.ProductDto;
import com.javPOL.magazineJava.model.Category;
import com.javPOL.magazineJava.model.User;
import com.javPOL.magazineJava.service.CategoryService;
import com.javPOL.magazineJava.service.ProductService;
import com.javPOL.magazineJava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;

    private final CategoryService categoryService;

    private final ProductService productService;

    public DataInitializer(UserService userService, CategoryService categoryService, ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userService.findByUsername("admin").isEmpty()) {
            userService.registerUser("admin", "admin123", "admin@example.com", "ADMIN");
            System.out.println("Created admin user: admin/admin123");
        }

        if (userService.findByUsername("user").isEmpty()) {
            userService.registerUser("user", "user123", "user@example.com", "USER");
            System.out.println("Created test user: user/user123");
        }

        if (categoryService.findAll().isEmpty()) {
            categoryService.save(new Category(1L, "Komputer"));
            categoryService.save(new Category(2L, "Ram"));
            categoryService.save(new Category(3L, "Płyta główna"));
            categoryService.save(new Category(4L, "Dysk"));
            categoryService.save(new Category(5L, "Karta Graficzna"));
            categoryService.save(new Category(6L, "Procesor"));
        }
        if (productService.findAll().isEmpty()) {
            productService.create(new ProductDto("KomputerXxX360Sigma",7500,2137.69,"Szybki ale głupi",1L));
            productService.create(new ProductDto("Ramex 8GB 2137 MHz", 100, 120, "Niezawodna pamięć ram niezawodnej firmy RAMEX sp.zoo", 2L));
            productService.create(new ProductDto("KIn Furras 16GB 3600MHz", 200, 260, "Kingston Empireum", 2L));
            productService.create(new ProductDto("ThinkFast X12 Pro", 1800, 4899.99, "Ultrabook dla profesjonalistów z aluminiową obudową", 1L));
            productService.create(new ProductDto("HyperRam 32GB 6000MHz", 150, 699, "Pamięć DDR5 o wysokiej wydajności", 2L));
            productService.create(new ProductDto("ASRock Z790 Phantom Gaming", 1200, 899, "Płyta główna z chipsetem Z790 dla graczy", 3L));
            productService.create(new ProductDto("MSI A520M-A PRO", 950, 289, "Ekonomiczna płyta główna z obsługą DDR4", 3L));
            productService.create(new ProductDto("Samsung 980 PRO 2TB", 50, 1099, "Dysk SSD NVMe do zastosowań profesjonalnych", 4L));
            productService.create(new ProductDto("Seagate Barracuda 1TB", 400, 219, "Tani dysk HDD do archiwizacji danych", 4L));
            productService.create(new ProductDto("NVIDIA RTX 4070 Ti", 1500, 3499, "Wydajna karta graficzna dla graczy", 5L));
        }
    }
}