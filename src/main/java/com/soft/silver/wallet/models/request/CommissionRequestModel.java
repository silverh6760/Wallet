package com.soft.silver.wallet.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommissionRequestModel {

    @Schema(description = "Define the reseller ID", example = "1")
    @NotNull(message = "resellerId is null")
    private long resellerId;

    @Schema(description = "Define the product ID", example = "1")
    @NotNull(message = "productId is null")
    private long productId;

    @Schema(description = "Define the commission rate", example = "0.2")
    @NotNull(message = "commissionRate is null")
    private double commissionRate;

}
