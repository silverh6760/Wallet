package com.soft.silver.wallet.controller;

import com.soft.silver.wallet.controller.mapper.CommissionMapper;
import com.soft.silver.wallet.exception.ExceptionMessage;
import com.soft.silver.wallet.exception.ServiceException;
import com.soft.silver.wallet.models.entity.Product;
import com.soft.silver.wallet.models.entity.Reseller;
import com.soft.silver.wallet.models.request.CommissionRequestModel;
import com.soft.silver.wallet.service.CommissionService;
import com.soft.silver.wallet.service.ProductService;
import com.soft.silver.wallet.service.ResellerService;
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
            @ApiResponse(responseCode = "500", description = ExceptionMessage.AN_EXCEPTION_OCCURRED_IN_WALLET_RESPONSE_MSG +
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
