package com.telecom.fanap.wallet.repository;

import com.telecom.fanap.wallet.models.entity.Reseller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResellerRepository extends JpaRepository<Reseller, Long> {

    Optional<Reseller> findResellerByCode(@Param("code") String code);

}
