package com.telecom.fanap.wallet.service;

import com.telecom.fanap.wallet.exception.ExceptionMessage;
import com.telecom.fanap.wallet.exception.ServiceException;
import com.telecom.fanap.wallet.models.entity.Commission;
import com.telecom.fanap.wallet.repository.CommissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommissionServiceImpl implements CommissionService{

    private CommissionRepository commissionRepository;

    public long createCommission(Commission commission) throws ServiceException {
        Optional<Commission> commissionFromDB = commissionRepository.findCommissionByResellerAndAndProduct(commission.getReseller(),
                commission.getProduct());
        if (commissionFromDB.isPresent()) {
            throw new ServiceException(ExceptionMessage.RECORD_IS_REPETITIVE,
                    new Object[]{commission.getReseller(), commission.getProduct()});
        }
        return commissionRepository.save(commission).getId();
    }
}
