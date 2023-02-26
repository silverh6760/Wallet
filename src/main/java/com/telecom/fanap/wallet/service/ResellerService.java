package com.telecom.fanap.wallet.service;

import com.telecom.fanap.wallet.exception.ServiceException;
import com.telecom.fanap.wallet.models.entity.Reseller;

public interface ResellerService {

    Long createReseller(Reseller reseller) throws ServiceException;

    Reseller findResellerById(long id) throws ServiceException;
}
