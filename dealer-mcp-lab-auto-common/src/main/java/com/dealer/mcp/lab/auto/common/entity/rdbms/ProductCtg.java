package com.dealer.mcp.lab.auto.common.entity.rdbms;

import com.dealer.mcp.lab.auto.common.entity.core.ProductCtgCore;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "product_ctgy")
@EqualsAndHashCode(callSuper = true)
public class ProductCtg extends ProductCtgCore {

}
