package com.dealer.mcp.lab.auto.common.svc.core.impl;

import com.dealer.mcp.lab.auto.common.dao.rdbms.ProductDiscountDao;
import com.dealer.mcp.lab.auto.common.dto.core.ProductDiscountCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductDiscountCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductDiscount;
import com.dealer.mcp.lab.auto.common.svc.core.ProductDiscountSvc;
import org.springframework.stereotype.Service;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
public class ProductDiscountSvcImpl extends AbstractAppCrudSvcImpl<ProductDiscount, ProductDiscountCrudReq, ProductDiscountCrudResp, ProductDiscountDao> implements ProductDiscountSvc {

    public ProductDiscountSvcImpl(ProductDiscountDao crudDao) {
        super(crudDao);
    }
}
