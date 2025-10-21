package com.dealer.mcp.lab.auto.common.entity.core;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductCore extends AbstractCoreNameDescEntity {

    @Column(name = "prod_ctg_id")
    private String productCtgId;
}
