package com.soft.silver.wallet.service;

import com.soft.silver.wallet.exception.ExceptionMessage;
import com.soft.silver.wallet.exception.ServiceException;
import com.soft.silver.wallet.models.entity.Product;
import com.soft.silver.wallet.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    public Long createProducts(Product product) {
        return productRepository.save(product).getId();
    }

    public Product findProductById(long id) throws ServiceException {
        return productRepository.findById(id).orElseThrow(() ->
                new ServiceException(ExceptionMessage.RECORD_NOT_FOUND, new Object[]{id}));
    }
}
