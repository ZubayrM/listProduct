package com.zubayr.listProduct.model;

import com.zubayr.listProduct.dto.ProductDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
@EqualsAndHashCode
@ApiModel(value = "Product", description = "Entity model")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")

    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "kcal")
    private Integer kcal;

    public static Product createProduct(ProductDto dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setKcal(dto.getKcal());
        return product;
    }
}
