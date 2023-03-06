package com.soft.silver.wallet.models.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Data
@Table(name = "interview_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name")
    @NotNull(message = "name is null")
    @NotEmpty(message = "name is empty")
    private String name;

    @Column(name = "price")
    @NotNull(message = "price is null")
    private Integer price;

}
