package com.soft.silver.wallet.service;

import com.soft.silver.wallet.exception.ExceptionMessage;
import com.soft.silver.wallet.exception.ServiceException;
import com.soft.silver.wallet.models.entity.Commission;
import com.soft.silver.wallet.repository.CommissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Commission> getAllCommissions(Integer pageSize, Integer pageNumber) {
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);
        return commissionRepository.findAll(pageable).stream().toList();
    }


    public Commission findCommissionById(long id) throws ServiceException {
        Optional<Commission> byId = commissionRepository.findById(id);
        return byId.orElseThrow(() ->
                new ServiceException(ExceptionMessage.RECORD_NOT_FOUND, new Object[]{id}));
    }


    public long updateCommission(Commission commission, long id) throws ServiceException {
        Commission commissionById = findCommissionById(id);
        commission.setId(commissionById.getId());
        return createCommission(commission);
    }


    public void deleteCommissionById(long id) {
        commissionRepository.deleteById(id);
    }

    public void deleteAllCommissions() {
        commissionRepository.deleteAll();
    }
}
