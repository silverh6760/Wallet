package com.telecom.fanap.wallet.repository;

import com.telecom.fanap.wallet.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
