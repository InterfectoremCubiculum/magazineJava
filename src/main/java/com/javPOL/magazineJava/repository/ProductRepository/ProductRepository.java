package com.javPOL.magazineJava.repository.ProductRepository;
import com.javPOL.magazineJava.model.Product;
import com.javPOL.magazineJava.repository.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository extends Repository<Product, Long> {
    Page<Product> findAll(Pageable pageable, Long categoryId);
}
