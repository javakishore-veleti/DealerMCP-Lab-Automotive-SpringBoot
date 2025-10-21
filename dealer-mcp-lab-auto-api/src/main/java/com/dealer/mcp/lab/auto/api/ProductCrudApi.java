package com.dealer.mcp.lab.auto.api;

import com.dealer.mcp.lab.auto.common.dto.core.ProductCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.Product;
import com.dealer.mcp.lab.auto.common.svc.core.ProductCrudSvc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/dealer-mcp/services/product/v1")
public class ProductCrudApi extends AbstractAppCrudApi<Product, ProductCrudReq, ProductCrudResp, ProductCrudSvc> {

    public ProductCrudApi(ProductCrudSvc crudSvc) {
        super(crudSvc, ProductCrudReq.class, ProductCrudResp.class);
    }
}
