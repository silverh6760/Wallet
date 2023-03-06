package com.soft.silver.wallet.controller;

import com.soft.silver.wallet.MotherObject;
import com.soft.silver.wallet.config.SecurityConfig;
import com.soft.silver.wallet.controller.mapper.CommissionMapper;
import com.soft.silver.wallet.models.entity.Commission;
import com.soft.silver.wallet.models.entity.Product;
import com.soft.silver.wallet.models.entity.Reseller;
import com.soft.silver.wallet.models.request.CommissionRequestModel;
import com.soft.silver.wallet.service.CommissionServiceImpl;
import com.soft.silver.wallet.service.ProductServiceImpl;
import com.soft.silver.wallet.service.ResellerServiceImpl;
import com.soft.silver.wallet.service.TokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.soft.silver.wallet.MotherObject.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {CommissionController.class, AuthController.class})
@Import(value = {SecurityConfig.class, TokenService.class})
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
        when(commissionMapper.toCommission(any(), any(), anyDouble())).thenReturn(anyCommission);
        when(commissionService.createCommission(any())).thenReturn(ANY_ID);

        MvcResult mvcResult = this.mockMvc.perform(post("/token")
                        .with(httpBasic("admin", "password")))
                .andExpect(status().isOk())
                .andReturn();
        String accessToken = mvcResult.getResponse().getContentAsString();

        this.mockMvc.perform(post(url)
                        .content(MotherObject.asJsonString(anyCommissionRequestModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().is2xxSuccessful());
    }
}
