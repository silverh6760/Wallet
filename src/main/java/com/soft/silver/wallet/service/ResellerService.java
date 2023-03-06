package com.soft.silver.wallet.service;

import com.soft.silver.wallet.exception.ServiceException;
import com.soft.silver.wallet.models.entity.Reseller;

public interface ResellerService {

    Long createReseller(Reseller reseller) throws ServiceException;

    Reseller findResellerById(long id) throws ServiceException;
}
