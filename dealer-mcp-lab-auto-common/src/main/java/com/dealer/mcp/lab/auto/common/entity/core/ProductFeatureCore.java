package com.dealer.mcp.lab.auto.common.entity.core;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductFeatureCore extends AbstractCoreNameDescEntity {

    @Column(name = "product_id")
    private String productId;
}
