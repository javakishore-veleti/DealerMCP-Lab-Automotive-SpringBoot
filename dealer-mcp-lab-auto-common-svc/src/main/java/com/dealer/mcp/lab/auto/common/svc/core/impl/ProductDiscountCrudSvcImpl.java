package com.dealer.mcp.lab.auto.common.svc.core.impl;

import com.dealer.mcp.lab.auto.common.dao.rdbms.ProductDiscountCrudDao;
import com.dealer.mcp.lab.auto.common.dto.core.ProductDiscountCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductDiscountCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductDiscount;
import com.dealer.mcp.lab.auto.common.svc.core.ProductDiscountCrudSvc;
import org.springframework.stereotype.Service;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
public class ProductDiscountCrudSvcImpl extends AbstractAppCrudSvcImpl<ProductDiscount, ProductDiscountCrudReq, ProductDiscountCrudResp, ProductDiscountCrudDao> implements ProductDiscountCrudSvc {

    public ProductDiscountCrudSvcImpl(ProductDiscountCrudDao crudDao) {
        super(crudDao);
    }
}
