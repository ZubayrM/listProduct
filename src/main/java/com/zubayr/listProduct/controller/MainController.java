package com.zubayr.listProduct.controller;

import com.zubayr.listProduct.dto.ListDto;
import com.zubayr.listProduct.dto.ListOfProductsDto;
import com.zubayr.listProduct.dto.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface MainController {

    ResponseEntity<ListDto> saveList(ListDto dto);

    ResponseEntity<ProductDto> saveProduct(ProductDto dto);

    ResponseEntity<ListOfProductsDto> addProductToList(Long productId, Long listId);

    ResponseEntity<Set<ProductDto>> allProduct();

    ResponseEntity<ListOfProductsDto> getById(Long id);

    ResponseEntity<?> updateNameList(Long listId, String name);

}
