package com.telecom.fanap.wallet.models.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Reseller reseller;

    private double commissionRate;


}
