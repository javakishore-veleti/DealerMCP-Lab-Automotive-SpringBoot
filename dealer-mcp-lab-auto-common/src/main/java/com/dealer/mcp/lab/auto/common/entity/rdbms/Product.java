package com.dealer.mcp.lab.auto.common.entity.rdbms;

import com.dealer.mcp.lab.auto.common.entity.core.ProductCore;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "product")
@EqualsAndHashCode(callSuper = true)
public class Product extends ProductCore {





}
