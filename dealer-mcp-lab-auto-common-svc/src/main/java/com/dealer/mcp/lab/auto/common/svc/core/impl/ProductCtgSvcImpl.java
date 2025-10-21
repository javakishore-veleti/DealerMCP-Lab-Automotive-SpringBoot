package com.dealer.mcp.lab.auto.common.svc.core.impl;

import com.dealer.mcp.lab.auto.common.dao.rdbms.ProductCtgDao;
import com.dealer.mcp.lab.auto.common.dao.rdbms.ProductDiscountDao;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCtgCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCtgCrudResp;
import com.dealer.mcp.lab.auto.common.dto.core.ProductDiscountCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductDiscountCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductCtg;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductDiscount;
import com.dealer.mcp.lab.auto.common.svc.core.ProductCtgSvc;
import com.dealer.mcp.lab.auto.common.svc.core.ProductDiscountSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@SuppressWarnings("ALL")
@Service
public class ProductCtgSvcImpl extends AbstractAppCrudSvcImpl<ProductCtg, ProductCtgCrudReq, ProductCtgCrudResp, ProductCtgDao> implements ProductCtgSvc {

    public ProductCtgSvcImpl(ProductCtgDao crudDao) {
        super(crudDao);
    }
}
