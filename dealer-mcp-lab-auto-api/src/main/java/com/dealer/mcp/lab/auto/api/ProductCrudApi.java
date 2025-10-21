package com.dealer.mcp.lab.auto.api;

import com.dealer.mcp.lab.auto.common.dto.core.ProductCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.Product;
import com.dealer.mcp.lab.auto.common.svc.core.ProductCrudSvc;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/dealer-mcp/services/product/v1")
@Tag(name = "Product", description = "CRUD operations for products")
public class ProductCrudApi extends AbstractAppCrudApi<Product, ProductCrudReq, ProductCrudResp, ProductCrudSvc> {

    public ProductCrudApi(ProductCrudSvc crudSvc) {
        super(crudSvc, ProductCrudReq.class, ProductCrudResp.class);
    }
}
