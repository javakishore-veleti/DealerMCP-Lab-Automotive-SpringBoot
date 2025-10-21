package com.dealer.mcp.lab.auto.common.dto.core;

import com.dealer.mcp.lab.auto.common.entity.rdbms.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductCrudResp extends AbstractCrudResp<Product> {
}
