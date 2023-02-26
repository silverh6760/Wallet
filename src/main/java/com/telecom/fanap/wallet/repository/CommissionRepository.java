package com.telecom.fanap.wallet.repository;

import com.telecom.fanap.wallet.models.entity.Commission;
import com.telecom.fanap.wallet.models.entity.Product;
import com.telecom.fanap.wallet.models.entity.Reseller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommissionRepository extends JpaRepository<Commission,Long> {

    Optional<Commission> findCommissionByResellerAndAndProduct(Reseller reseller, Product product);
}
