package com.telecom.fanap.wallet.service;

import com.telecom.fanap.wallet.exception.ExceptionMessage;
import com.telecom.fanap.wallet.exception.ServiceException;
import com.telecom.fanap.wallet.models.entity.Product;
import com.telecom.fanap.wallet.repository.ProductRepository;
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
