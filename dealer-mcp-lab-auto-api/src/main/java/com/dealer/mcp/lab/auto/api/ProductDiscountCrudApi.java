package com.dealer.mcp.lab.auto.api;

import com.dealer.mcp.lab.auto.common.dto.core.ProductDiscountCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductDiscountCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductDiscount;
import com.dealer.mcp.lab.auto.common.svc.core.ProductDiscountCrudSvc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/dealer-mcp/services/product-discount/v1")
public class ProductDiscountCrudApi extends AbstractAppCrudApi<ProductDiscount, ProductDiscountCrudReq, ProductDiscountCrudResp, ProductDiscountCrudSvc> {

    public ProductDiscountCrudApi(ProductDiscountCrudSvc crudSvc) {
        super(crudSvc, ProductDiscountCrudReq.class, ProductDiscountCrudResp.class);
    }
}
