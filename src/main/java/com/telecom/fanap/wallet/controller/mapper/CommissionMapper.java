package com.telecom.fanap.wallet.controller.mapper;

import com.telecom.fanap.wallet.models.entity.Commission;
import com.telecom.fanap.wallet.models.entity.Product;
import com.telecom.fanap.wallet.models.entity.Reseller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommissionMapper {
    @Mapping(target = "id", ignore = true)
    Commission toCommission(Product product, Reseller reseller, Double commissionRate);
}
