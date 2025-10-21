package com.dealer.mcp.lab.auto.api;

import com.dealer.mcp.lab.auto.common.dto.core.ProductFeatureCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductFeatureCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductFeature;
import com.dealer.mcp.lab.auto.common.svc.core.ProductFeatureCrudSvc;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/dealer-mcp/services/product-feature/v1")
@Tag(name = "Product Feature", description = "CRUD operations for product features")
public class ProductFeatureCrudApi extends AbstractAppCrudApi<ProductFeature, ProductFeatureCrudReq, ProductFeatureCrudResp, ProductFeatureCrudSvc> {

    public ProductFeatureCrudApi(ProductFeatureCrudSvc crudSvc) {
        super(crudSvc, ProductFeatureCrudReq.class, ProductFeatureCrudResp.class);
    }
}
