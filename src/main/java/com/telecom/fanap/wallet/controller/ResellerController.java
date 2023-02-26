package com.telecom.fanap.wallet.controller;

import com.telecom.fanap.wallet.exception.ServiceException;
import com.telecom.fanap.wallet.models.entity.Reseller;
import com.telecom.fanap.wallet.service.ResellerService;
import com.telecom.fanap.wallet.service.ResellerServiceImpl;
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

@AllArgsConstructor
@RestController
@RequestMapping(ResellerController.RESELLER_CONTROLLER_ADDRESS)
@Validated
@Tag(name = "Reseller Controller", description = "It manages crud operations on reseller entity")
public class ResellerController {
    public static final String RESELLER_CONTROLLER_ADDRESS = "/resellers";

    private ResellerService resellerService;

    @PostMapping
    @Operation(summary = "Create reseller")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = AN_EXCEPTION_OCCURRED_IN_WALLET_RESPONSE_MSG),
    })
    public long createReseller(@Parameter(description = "A new reseller data")
                               @Valid @RequestBody Reseller reseller) throws ServiceException {
        return resellerService.createReseller(reseller);
    }
}
