package com.dealer.mcp.lab.auto.common.svc.core.impl;

import com.dealer.mcp.lab.auto.common.dao.rdbms.ProductCtgCrudDao;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCtgCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCtgCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductCtg;
import com.dealer.mcp.lab.auto.common.svc.core.ProductCtgCrudSvc;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public class ProductCtgCrudSvcImpl extends AbstractAppCrudSvcImpl<ProductCtg, ProductCtgCrudReq, ProductCtgCrudResp, ProductCtgCrudDao> implements ProductCtgCrudSvc {

    public ProductCtgCrudSvcImpl(ProductCtgCrudDao crudDao) {
        super(crudDao);
    }
}
