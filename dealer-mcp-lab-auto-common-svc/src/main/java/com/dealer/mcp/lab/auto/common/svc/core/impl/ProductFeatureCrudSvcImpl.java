package com.dealer.mcp.lab.auto.common.svc.core.impl;

import com.dealer.mcp.lab.auto.common.dao.rdbms.ProductFeatureCrudDao;
import com.dealer.mcp.lab.auto.common.dto.core.ProductFeatureCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductFeatureCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductFeature;
import com.dealer.mcp.lab.auto.common.svc.core.ProductFeatureCrudSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductFeatureCrudSvcImpl extends AbstractAppCrudSvcImpl<ProductFeature, ProductFeatureCrudReq, ProductFeatureCrudResp, ProductFeatureCrudDao> implements ProductFeatureCrudSvc {

    public ProductFeatureCrudSvcImpl(ProductFeatureCrudDao crudDao) {
        super(crudDao);
    }

    @Override
    public void getFeaturesByProductId(ProductFeatureCrudReq req, ProductFeatureCrudResp resp) {
        log.info("STARTED getFeaturesByProductId called ProductId {}", req.getProductId());

        List<ProductFeature> productFeatureList = super.getCrudDao().findByProductId(req.getProductId());
        resp.setEntityList(productFeatureList);

        log.info("COMPLETED getFeaturesByProductId called ProductId {}", req.getProductId());
    }
}
