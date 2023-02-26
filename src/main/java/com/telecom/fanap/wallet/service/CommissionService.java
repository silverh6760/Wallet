package com.telecom.fanap.wallet.service;

import com.telecom.fanap.wallet.exception.ServiceException;
import com.telecom.fanap.wallet.models.entity.Commission;

public interface CommissionService {

     long createCommission(Commission commission) throws ServiceException;
}
