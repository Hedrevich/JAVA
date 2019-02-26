package com.leverx.leverxspringproj.controller;

import java.util.List;

import com.leverx.leverxspringproj.domain.Product;
import com.leverx.leverxspringproj.service.ProductService;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {

	@Autowired
    public ProductService productService;

    @GetMapping(value="/products")
    public List<Product> getAllProducts() throws ODataException {
        return  productService.getProductsOdata("OData");
    }

}
