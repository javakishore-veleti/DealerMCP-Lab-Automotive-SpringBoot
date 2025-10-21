package com.dealer.mcp.lab.auto.common.entity.rdbms;

import com.dealer.mcp.lab.auto.common.entity.core.ProductFeatureCore;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "product_feature")
@EqualsAndHashCode(callSuper = true)
public class ProductFeature extends ProductFeatureCore {

}
