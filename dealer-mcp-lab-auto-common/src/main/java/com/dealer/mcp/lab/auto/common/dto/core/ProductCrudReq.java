package com.dealer.mcp.lab.auto.common.dto.core;

import com.dealer.mcp.lab.auto.common.entity.rdbms.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ProductCrudReq extends AbstractCrudReq<Product> {
}
