package com.soft.silver.wallet.service;

import com.soft.silver.wallet.exception.ExceptionMessage;
import com.soft.silver.wallet.exception.ServiceException;
import com.soft.silver.wallet.models.entity.Commission;
import com.soft.silver.wallet.repository.CommissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommissionServiceImpl implements CommissionService{

    private CommissionRepository commissionRepository;

    public long createCommission(Commission commission) throws ServiceException {
        Optional<Commission> commissionFromDB = commissionRepository.findCommissionByResellerAndProduct(commission.getReseller(),
                commission.getProduct());
        if (commissionFromDB.isPresent()) {
            throw new ServiceException(ExceptionMessage.RECORD_IS_REPETITIVE,
                    new Object[]{commission.getReseller(), commission.getProduct()});
        }
        return commissionRepository.save(commission).getId();
    }
}
