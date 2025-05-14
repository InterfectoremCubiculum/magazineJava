package com.javPOL.magazineJava.repository;
import com.javPOL.magazineJava.model.ProductSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, Integer> {
    List<ProductSupplier> findBySupplierId(Integer supplierId);
    List<ProductSupplier> findByProductId(Integer productId);
}
