package com.dealer.mcp.lab.auto.common.svc.core;

import com.dealer.mcp.lab.auto.common.dto.core.ProductCtgCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCtgCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductCtg;
import com.dealer.mcp.lab.auto.common.svc.AppCrudSvc;

public interface ProductCtgSvc extends AppCrudSvc<ProductCtg, ProductCtgCrudReq, ProductCtgCrudResp> {

}
