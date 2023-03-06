package com.soft.silver.wallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soft.silver.wallet.models.entity.Commission;
import com.soft.silver.wallet.models.entity.Product;
import com.soft.silver.wallet.models.entity.Reseller;
import com.soft.silver.wallet.models.request.CommissionRequestModel;

public class MotherObject {
    public static final String COMMISSION_CONTROLLER_ADDRESS = "/commissions";
    public static final String ANY_PRODUCT_NAME = "p1";
    public static final String ANY_RESELLER_NAME = "r1";
    public static final String ANY_RESELLER_CODE = "abc123";
    public static final double ANY_COMMISSION_RATE = 0.2;
    public static final long ANY_ID = 1;
    public static final int ANY_PRICE = 100;


    public static CommissionRequestModel createAnyCommissionRequestModel() {
        CommissionRequestModel commissionRequestModel = new CommissionRequestModel();
        commissionRequestModel.setCommissionRate(ANY_COMMISSION_RATE);
        commissionRequestModel.setProductId(ANY_ID);
        commissionRequestModel.setResellerId(ANY_ID);
        return commissionRequestModel;
    }

    public static Product createAnyProduct() {
        Product product = new Product();
        product.setId(ANY_ID);
        product.setName(ANY_PRODUCT_NAME);
        product.setPrice(ANY_PRICE);
        return product;
    }

    public static Reseller createAnyReseller() {
        Reseller reseller = new Reseller();
        reseller.setId(ANY_ID);
        reseller.setName(ANY_RESELLER_NAME);
        reseller.setCode(ANY_RESELLER_CODE);
        return reseller;
    }

    public static Commission createAnyCommission() {
        Commission commission = new Commission();
        commission.setId(ANY_ID);
        commission.setProduct(createAnyProduct());
        commission.setReseller(createAnyReseller());
        commission.setCommissionRate(ANY_COMMISSION_RATE);
        return commission;
    }

    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
