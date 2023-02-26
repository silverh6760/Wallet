package com.telecom.fanap.wallet.controller;

import com.telecom.fanap.wallet.controller.mapper.CommissionMapper;
import com.telecom.fanap.wallet.exception.ServiceException;
import com.telecom.fanap.wallet.models.entity.Product;
import com.telecom.fanap.wallet.models.entity.Reseller;
import com.telecom.fanap.wallet.models.request.CommissionRequestModel;
import com.telecom.fanap.wallet.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.telecom.fanap.wallet.exception.ExceptionMessage.AN_EXCEPTION_OCCURRED_IN_WALLET_RESPONSE_MSG;

@RestController
@AllArgsConstructor
@RequestMapping(CommissionController.COMMISSION_CONTROLLER_ADDRESS)
@Validated
@Tag(name = "Commission Controller", description = "It manages crud operations on commission entity")
public class CommissionController {

    public static final String COMMISSION_CONTROLLER_ADDRESS = "/commissions";

    private CommissionService commissionService;
    private ProductService productService;
    private ResellerService resellerService;
    private CommissionMapper commissionMapper;

    @PostMapping
    @Operation(summary = "Create commission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = AN_EXCEPTION_OCCURRED_IN_WALLET_RESPONSE_MSG +
                    "when entities are not present"),
    })
    public long createCommission(@Parameter(description = "The request body of a new commission")
                                 @Valid @RequestBody CommissionRequestModel commissionRequestModel) throws ServiceException {
        Product productById = productService.findProductById(commissionRequestModel.getProductId());
        Reseller resellerByCode = resellerService.findResellerById(commissionRequestModel.getResellerId());
        return commissionService.createCommission(commissionMapper.toCommission(productById, resellerByCode,
                commissionRequestModel.getCommissionRate()));
    }
}
