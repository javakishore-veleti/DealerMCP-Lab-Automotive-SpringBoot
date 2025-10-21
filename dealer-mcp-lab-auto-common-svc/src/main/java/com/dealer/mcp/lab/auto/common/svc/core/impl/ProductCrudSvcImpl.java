package com.dealer.mcp.lab.auto.common.svc.core.impl;

import com.dealer.mcp.lab.auto.common.dao.rdbms.ProductCrudDao;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.Product;
import com.dealer.mcp.lab.auto.common.svc.core.ProductCrudSvc;
import org.springframework.stereotype.Service;

@Service
public class ProductCrudSvcImpl extends AbstractAppCrudSvcImpl<Product, ProductCrudReq, ProductCrudResp, ProductCrudDao> implements ProductCrudSvc {

    public ProductCrudSvcImpl(ProductCrudDao crudDao) {
        super(crudDao);
    }
}
