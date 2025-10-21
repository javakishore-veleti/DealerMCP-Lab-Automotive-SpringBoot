package com.dealer.mcp.lab.auto.common.svc.core.impl;

import com.dealer.mcp.lab.auto.common.dao.rdbms.ProductDao;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.Product;
import com.dealer.mcp.lab.auto.common.svc.core.ProductSvc;
import org.springframework.stereotype.Service;

@Service
public class ProductSvcImpl extends AbstractAppCrudSvcImpl<Product, ProductCrudReq, ProductCrudResp, ProductDao> implements ProductSvc {

    public ProductSvcImpl(ProductDao crudDao) {
        super(crudDao);
    }
}
