package com.zubayr.listProduct.service;

import com.zubayr.listProduct.dto.ListDto;
import com.zubayr.listProduct.dto.ListOfProductsDto;
import com.zubayr.listProduct.dto.ProductDto;
import com.zubayr.listProduct.exception.MaxCountToListException;
import com.zubayr.listProduct.model.List;
import com.zubayr.listProduct.model.Product;
import com.zubayr.listProduct.repository.ListRepository;
import com.zubayr.listProduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.yml")
public class MainService {

    private final ListRepository listRepository;
    private final ProductRepository productRepository;

    @Value("${max_count}")
    private Long maxCount;

    @Autowired
    public MainService(ListRepository listRepository, ProductRepository productRepository) {
        this.listRepository = listRepository;
        this.productRepository = productRepository;
    }

    public ResponseEntity<ListDto> saveList(ListDto dto) {
        if (listRepository.count() < maxCount)
            return  ResponseEntity.ok(ListDto.createDto(listRepository.save(List.createList(dto))));
        else
            throw new MaxCountToListException();
    }

    public ResponseEntity<ProductDto> saveProduct(ProductDto dto) {
        return ResponseEntity.ok(ProductDto.createDto(productRepository.save(Product.createProduct(dto))));
    }

    public ResponseEntity<ListOfProductsDto> addProductToList(Long productId, Long listId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Optional<List> optionalList = listRepository.findById(listId);
        optionalList.ifPresent(list -> {
            list.addProduct(optionalProduct.orElseThrow());
            listRepository.save(list);
        });
        return ResponseEntity.ok(ListOfProductsDto.createDto(listRepository.findById(listId).orElseThrow()));
    }

    public ResponseEntity<Set<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAllProduct());
    }

    public ResponseEntity<ListOfProductsDto> getListOfProduct(Long listId) {
        return listRepository
                .findById(listId)
                .map(list -> ResponseEntity.ok(ListOfProductsDto.createDto(list)))
                .orElse(null);

    }

    public ResponseEntity<?> updateNameList(Long listId, String newName) throws ChangeSetPersister.NotFoundException {
        listRepository.updateNameList(listId, newName);
        return ResponseEntity.ok(listRepository.findById(listId).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }
}
