package com.dealer.mcp.lab.auto.common.svc.core.impl;

import com.dealer.mcp.lab.auto.common.dao.rdbms.ProductFeatureCrudDao;
import com.dealer.mcp.lab.auto.common.dto.core.ProductFeatureCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductFeatureCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductFeature;
import com.dealer.mcp.lab.auto.common.svc.core.ProductFeatureCrudSvc;
import org.springframework.stereotype.Service;

@Service
public class ProductFeatureCrudSvcImpl extends AbstractAppCrudSvcImpl<ProductFeature, ProductFeatureCrudReq, ProductFeatureCrudResp, ProductFeatureCrudDao> implements ProductFeatureCrudSvc {

    public ProductFeatureCrudSvcImpl(ProductFeatureCrudDao crudDao) {
        super(crudDao);
    }
}
