package com.zubayr.listProduct.dto;

import com.zubayr.listProduct.model.List;
import com.zubayr.listProduct.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ListOfProductDto" ,description = "List of product, response model")
public class ListOfProductsDto {

    @ApiParam("List name")
    private String listName;

    @ApiParam("List productions")
    private Set<ProductDto> products;

    @ApiParam("Size kcal")
    private Integer kcal;

    public static ListOfProductsDto createDto(List list) {
        return new ListOfProductsDto(
                list.getName(),
                list.getProducts().stream().map(ProductDto::createDto).collect(Collectors.toSet()),
                list.getProducts().stream().map(Product::getKcal).flatMapToInt(IntStream::of).sum()
                );
    }
}
