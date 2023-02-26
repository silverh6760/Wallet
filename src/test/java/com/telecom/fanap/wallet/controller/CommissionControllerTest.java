package com.telecom.fanap.wallet.controller;

import com.telecom.fanap.wallet.MotherObject;
import com.telecom.fanap.wallet.controller.mapper.CommissionMapper;
import com.telecom.fanap.wallet.models.entity.Commission;
import com.telecom.fanap.wallet.models.entity.Product;
import com.telecom.fanap.wallet.models.entity.Reseller;
import com.telecom.fanap.wallet.models.request.CommissionRequestModel;
import com.telecom.fanap.wallet.service.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.telecom.fanap.wallet.MotherObject.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(value = CommissionController.class)
class CommissionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResellerServiceImpl resellerService;

    @MockBean
    private ProductServiceImpl productService;

    @MockBean
    private CommissionServiceImpl commissionService;

    @MockBean
    private CommissionMapper commissionMapper;

    @Test
    @DisplayName("The commission is inserted with reseller and product info.")
    void given_commissionRequestModel_when_createCommission_then_must_return_200() throws Exception {
        String url = String.format("%s", COMMISSION_CONTROLLER_ADDRESS);
        CommissionRequestModel anyCommissionRequestModel = createAnyCommissionRequestModel();
        Product anyProduct = createAnyProduct();
        Reseller anyReseller = createAnyReseller();
        Commission anyCommission = createAnyCommission();

        when(productService.findProductById(anyLong())).thenReturn(anyProduct);
        when(resellerService.findResellerById(anyLong())).thenReturn(anyReseller);
        when(commissionMapper.toCommission(any(),any(),anyDouble())).thenReturn(anyCommission);
        when(commissionService.createCommission(any())).thenReturn(ANY_ID);

        this.mockMvc.perform(post(url)
                        .content(MotherObject.asJsonString(anyCommissionRequestModel))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
