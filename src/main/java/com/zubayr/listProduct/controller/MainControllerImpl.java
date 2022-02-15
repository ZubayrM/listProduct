package com.zubayr.listProduct.controller;

import com.zubayr.listProduct.dto.ListDto;
import com.zubayr.listProduct.dto.ListOfProductsDto;
import com.zubayr.listProduct.dto.ProductDto;
import com.zubayr.listProduct.service.MainService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/")
public class MainControllerImpl implements MainController{

    private final MainService mainService;

    @Autowired
    public MainControllerImpl(MainService mainService) {
        this.mainService = mainService;

    }

    @PostMapping("/save_list")
    @Override
    @ApiOperation("Save List")
    public ResponseEntity<ListDto> saveList(@RequestBody ListDto dto) {
        return mainService.saveList(dto);
    }

    @PostMapping("/save_product")
    @Override
    @ApiOperation("Save product")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto dto) {
        return mainService.saveProduct(dto);
    }

    @PutMapping("/{product_id}/add/{list_id}")
    @Override
    @ApiOperation("Add Product to List")
    public ResponseEntity<ListOfProductsDto> addProductToList(@PathVariable("product_id") Long productId,@PathVariable("list_id") Long listId) {
        return mainService.addProductToList(productId, listId);
    }

    @GetMapping("/all_product")
    @Override
    @ApiOperation("Get all Products")
    public ResponseEntity<Set<ProductDto>> allProduct() {
        return mainService.getAllProducts();
    }

    @GetMapping("/list/{id}")
    @Override
    @ApiOperation("Get list of Products")
    public ResponseEntity<ListOfProductsDto> getById(@PathVariable Long id) {
        return mainService.getListOfProduct(id);
    }

    @Override
    @PutMapping("/updateNameList/{id}")
    @ApiOperation("Change list name")
    @Transactional
    public ResponseEntity<?> updateNameList(@PathVariable(name = "id") Long listId, @RequestParam(name = "new_name") String newName) {
        try {
            return mainService.updateNameList(listId, newName);
        } catch (ChangeSetPersister.NotFoundException e){
            return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND);
        }
    }
}
