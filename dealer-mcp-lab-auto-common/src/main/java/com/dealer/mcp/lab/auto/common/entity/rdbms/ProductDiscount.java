package com.dealer.mcp.lab.auto.common.entity.rdbms;

import com.dealer.mcp.lab.auto.common.entity.core.ProductDiscountCore;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "product_discount")
@EqualsAndHashCode(callSuper = true)
public class ProductDiscount extends ProductDiscountCore {

}
