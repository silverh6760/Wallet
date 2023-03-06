package com.soft.silver.wallet.controller;

import com.soft.silver.wallet.constant.Constant;
import com.soft.silver.wallet.controller.mapper.CommissionMapper;
import com.soft.silver.wallet.exception.ExceptionMessage;
import com.soft.silver.wallet.exception.ServiceException;
import com.soft.silver.wallet.models.entity.Commission;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    @Operation(summary = "Fetch all commissions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.AN_EXCEPTION_OCCURRED_IN_WALLET_RESPONSE_MSG +
                    "when entities are not present"),
    })
    public List<Commission> fetchAllCommissions(@Parameter(description = "The page size", example = "10")
                                                @RequestParam(value = "pageSize", required = false) Optional<Integer> pageSize,
                                                @Parameter(description = "The page number", example = "1")
                                                @RequestParam(value = "pageNumber", required = false) Optional<Integer> pageNumber) {
        final int finalPageSize = pageSize.orElse(Constant.PAGE_SIZE);
        final int finalPageNumber = pageNumber.orElse(Constant.PAGE_NUMBER);

        return commissionService.getAllCommissions(finalPageSize, finalPageNumber);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Fetch all commissions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.AN_EXCEPTION_OCCURRED_IN_WALLET_RESPONSE_MSG +
                    "when entities are not present"),
    })
    public Commission fetchCommissionById(@Parameter(description = "The Commission Id", example = "10")
                                          @PathVariable(value = "id") long id) throws ServiceException {
        return commissionService.findCommissionById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update commission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.AN_EXCEPTION_OCCURRED_IN_WALLET_RESPONSE_MSG +
                    "when entities are not present"),
    })
    public long updateCommission(@Parameter(description = "The request body of an existing commission")
                                 @Valid @RequestBody CommissionRequestModel commissionRequestModel,
                                 @PathVariable("id") long id) throws ServiceException {
        Product productById = productService.findProductById(commissionRequestModel.getProductId());
        Reseller resellerByCode = resellerService.findResellerById(commissionRequestModel.getResellerId());
        return commissionService.updateCommission(commissionMapper.toCommission(productById, resellerByCode,
                commissionRequestModel.getCommissionRate()), id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a commission by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.AN_EXCEPTION_OCCURRED_IN_WALLET_RESPONSE_MSG +
                    "when entities are not present"),
    })
    public void deleteCommissionById(@Parameter(description = "The Commission Id", example = "10")
                                     @PathVariable(value = "id") long id) throws ServiceException {
        commissionService.deleteCommissionById(id);
    }

    @DeleteMapping
    @Operation(summary = "Delete a commission by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.AN_EXCEPTION_OCCURRED_IN_WALLET_RESPONSE_MSG +
                    "when entities are not present"),
    })
    public void deleteAllCommissions() {
        commissionService.deleteAllCommissions();
    }

}
