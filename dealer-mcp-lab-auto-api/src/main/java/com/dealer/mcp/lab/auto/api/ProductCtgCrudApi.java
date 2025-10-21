package com.dealer.mcp.lab.auto.api;

import com.dealer.mcp.lab.auto.common.dto.core.ProductCtgCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCtgCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductCtg;
import com.dealer.mcp.lab.auto.common.svc.core.ProductCtgCrudSvc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/dealer-mcp/services/product-ctg/v1")
public class ProductCtgCrudApi extends AbstractAppCrudApi<ProductCtg, ProductCtgCrudReq, ProductCtgCrudResp, ProductCtgCrudSvc> {

    public ProductCtgCrudApi(ProductCtgCrudSvc crudSvc) {
        super(crudSvc, ProductCtgCrudReq.class, ProductCtgCrudResp.class);
    }
}
