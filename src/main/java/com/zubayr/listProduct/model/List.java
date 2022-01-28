package com.zubayr.listProduct.model;

import com.zubayr.listProduct.dto.ListDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "lists")
@ApiModel(value = "List", description = "Entity model")
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "list_id")
    private Set<Product> products;

    public void addProduct(Product product){
        this.products.add(product);
    }

    public static List createList(ListDto dto){
        List list = new List();
        list.setName(dto.getName());
        return list;
    }

}
