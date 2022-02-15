package com.zubayr.listProduct.repository;

import com.zubayr.listProduct.dto.ProductDto;
import com.zubayr.listProduct.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select new com.zubayr.listProduct.dto.ProductDto(name, description, kcal) from Product")
    Set<ProductDto> findAllProduct();

}
