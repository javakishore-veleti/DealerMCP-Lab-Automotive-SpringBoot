package com.dealer.mcp.lab.auto.common.dto.core;

import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductFeature;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductFeatureCrudResp extends AbstractCrudResp<ProductFeature> {
}
