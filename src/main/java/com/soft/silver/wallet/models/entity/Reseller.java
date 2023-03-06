package com.soft.silver.wallet.models.entity;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@DiscriminatorValue("reseller")
@Data
public class Reseller extends User {
    @NotNull(message = "code is null")
    @Column(name = "code")
    private String code;

}
