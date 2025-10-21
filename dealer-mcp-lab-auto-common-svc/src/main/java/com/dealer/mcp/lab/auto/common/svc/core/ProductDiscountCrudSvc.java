package com.dealer.mcp.lab.auto.common.svc.core;

import com.dealer.mcp.lab.auto.common.dto.core.ProductDiscountCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductDiscountCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductDiscount;
import com.dealer.mcp.lab.auto.common.svc.AppCrudSvc;

public interface ProductDiscountCrudSvc extends AppCrudSvc<ProductDiscount, ProductDiscountCrudReq, ProductDiscountCrudResp> {

}
