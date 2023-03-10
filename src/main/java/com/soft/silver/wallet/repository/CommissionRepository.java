package com.soft.silver.wallet.repository;

import com.soft.silver.wallet.models.entity.Commission;
import com.soft.silver.wallet.models.entity.Product;
import com.soft.silver.wallet.models.entity.Reseller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommissionRepository extends JpaRepository<Commission,Long> {

    Optional<Commission> findCommissionByResellerAndProduct(Reseller reseller, Product product);


}
