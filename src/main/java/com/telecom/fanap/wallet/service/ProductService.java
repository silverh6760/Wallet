package com.telecom.fanap.wallet.service;

import com.telecom.fanap.wallet.exception.ServiceException;
import com.telecom.fanap.wallet.models.entity.Product;

public interface ProductService {

    Long createProducts(Product product);

    Product findProductById(long id) throws ServiceException;
}
