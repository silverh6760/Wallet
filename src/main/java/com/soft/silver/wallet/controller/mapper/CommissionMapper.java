package com.soft.silver.wallet.controller.mapper;

import com.soft.silver.wallet.models.entity.Commission;
import com.soft.silver.wallet.models.entity.Product;
import com.soft.silver.wallet.models.entity.Reseller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommissionMapper {
    @Mapping(target = "id", ignore = true)
    Commission toCommission(Product product, Reseller reseller, Double commissionRate);
}
