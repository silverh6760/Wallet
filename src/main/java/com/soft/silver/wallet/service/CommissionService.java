package com.soft.silver.wallet.service;

import com.soft.silver.wallet.exception.ServiceException;
import com.soft.silver.wallet.models.entity.Commission;

public interface CommissionService {

     long createCommission(Commission commission) throws ServiceException;
}
