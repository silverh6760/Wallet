package com.soft.silver.wallet.service;

import com.soft.silver.wallet.exception.ExceptionMessage;
import com.soft.silver.wallet.exception.ServiceException;
import com.soft.silver.wallet.models.entity.Reseller;
import com.soft.silver.wallet.repository.ResellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ResellerServiceImpl implements ResellerService{

    private ResellerRepository resellerRepository;

    public Long createReseller(Reseller reseller) throws ServiceException {
        Optional<Reseller> resellerByCode = resellerRepository.findResellerByCode(reseller.getCode());
        if (resellerByCode.isPresent())
            throw new ServiceException(ExceptionMessage.RECORD_IS_REPETITIVE,
                    new Object[]{reseller.getCode()});
        return resellerRepository.save(reseller).getId();
    }

    public Reseller findResellerById(long id) throws ServiceException {
        return resellerRepository.findById(id).orElseThrow(() ->
                new ServiceException(ExceptionMessage.RECORD_NOT_FOUND, new Object[]{id}));
    }

}
