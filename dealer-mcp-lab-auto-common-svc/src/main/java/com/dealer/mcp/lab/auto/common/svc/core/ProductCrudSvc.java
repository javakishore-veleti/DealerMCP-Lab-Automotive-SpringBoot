package com.dealer.mcp.lab.auto.common.svc.core;

import com.dealer.mcp.lab.auto.common.dto.core.ProductCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.Product;
import com.dealer.mcp.lab.auto.common.svc.AppCrudSvc;

public interface ProductCrudSvc extends AppCrudSvc<Product, ProductCrudReq, ProductCrudResp> {
}
