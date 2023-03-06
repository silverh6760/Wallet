package com.soft.silver.wallet.service;

import com.soft.silver.wallet.exception.ServiceException;
import com.soft.silver.wallet.models.entity.Product;

public interface ProductService {

    Long createProducts(Product product);

    Product findProductById(long id) throws ServiceException;
}
