package com.telecom.fanap.wallet.service;

import com.telecom.fanap.wallet.exception.ServiceException;
import com.telecom.fanap.wallet.models.entity.Commission;
import com.telecom.fanap.wallet.repository.CommissionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.telecom.fanap.wallet.MotherObject.createAnyCommission;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CommissionServiceTest {

    @InjectMocks
    private CommissionServiceImpl commissionService;

    @Mock
    private CommissionRepository commissionRepository;

    @Test
    void given_commission_when_create_commission_then_must_return_id() throws ServiceException {
        Commission anyCommission = createAnyCommission();

        when(commissionRepository.findCommissionByResellerAndProduct(any(), any()))
                .thenReturn(Optional.empty());
        when(commissionRepository.save(any())).thenReturn(anyCommission);

        long id = commissionService.createCommission(anyCommission);

        Assertions.assertEquals(id, anyCommission.getId());
    }

    @Test
    void given_commission_when_createCommission_and_is_repetitive_then_must_throws_exception() {
        Commission anyCommission = createAnyCommission();

        when(commissionRepository.findCommissionByResellerAndProduct(any(), any()))
                .thenReturn(Optional.of(anyCommission));

        Assertions.assertThrows(ServiceException.class, () -> commissionService.createCommission(anyCommission));
    }
}
