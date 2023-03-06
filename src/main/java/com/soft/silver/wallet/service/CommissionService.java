package com.soft.silver.wallet.service;

import com.soft.silver.wallet.exception.ServiceException;
import com.soft.silver.wallet.models.entity.Commission;

import java.util.List;

public interface CommissionService {

    long createCommission(Commission commission) throws ServiceException;

    List<Commission> getAllCommissions(Integer pageSize, Integer pageNumber);

    Commission findCommissionById(long id) throws ServiceException;

    long updateCommission(Commission commission, long id) throws ServiceException;

    void deleteCommissionById(long id) throws ServiceException;

    void deleteAllCommissions();


}
