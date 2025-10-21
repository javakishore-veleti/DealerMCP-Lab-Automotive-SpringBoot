package com.dealer.mcp.lab.auto.common.svc.core;

import com.dealer.mcp.lab.auto.common.dto.core.ProductFeatureCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductFeatureCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductFeature;
import com.dealer.mcp.lab.auto.common.svc.AppCrudSvc;

public interface ProductFeatureCrudSvc extends AppCrudSvc<ProductFeature, ProductFeatureCrudReq, ProductFeatureCrudResp> {

    void getFeaturesByProductId(ProductFeatureCrudReq req, ProductFeatureCrudResp resp);
}
