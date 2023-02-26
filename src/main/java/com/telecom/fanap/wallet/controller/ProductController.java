package com.telecom.fanap.wallet.controller;

import com.telecom.fanap.wallet.models.entity.Product;
import com.telecom.fanap.wallet.service.ProductService;
import com.telecom.fanap.wallet.service.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.telecom.fanap.wallet.exception.ExceptionMessage.AN_EXCEPTION_OCCURRED_IN_WALLET_RESPONSE_MSG;

@AllArgsConstructor
@RestController
@RequestMapping(ProductController.PRODUCT_CONTROLLER_ADDRESS)
@Validated
@Tag(name = "Product Controller", description = "It manages crud operations on product entity")
public class ProductController {
    public static final String PRODUCT_CONTROLLER_ADDRESS = "/products";

    private ProductService productService;

    @PostMapping
    @Operation(summary = "Create a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = AN_EXCEPTION_OCCURRED_IN_WALLET_RESPONSE_MSG),
    })
    public Long postProduct(@Parameter(description = "New product data")
                            @Valid @RequestBody Product product) {
        return productService.createProducts(product);
    }
}
