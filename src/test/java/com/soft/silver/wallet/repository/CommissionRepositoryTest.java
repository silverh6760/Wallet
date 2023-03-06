package com.soft.silver.wallet.repository;

import com.soft.silver.wallet.WalletApplication;
import com.soft.silver.wallet.models.entity.Commission;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.soft.silver.wallet.MotherObject.createAnyCommission;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = WalletApplication.class)
class CommissionRepositoryTest {

    @Autowired
    private CommissionRepository commissionRepository;

    @Test
    void FindCommissionById() {
        Commission commission = createAnyCommission();
        commissionRepository.save(commission);
        Commission result = commissionRepository.findById(commission.getId()).get();
        assertEquals(commission.getId(), result.getId());
    }
}
