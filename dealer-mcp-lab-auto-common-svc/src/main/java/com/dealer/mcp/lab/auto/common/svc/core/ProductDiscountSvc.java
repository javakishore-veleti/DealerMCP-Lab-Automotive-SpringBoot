package com.dealer.mcp.lab.auto.common.svc.core;

import com.dealer.mcp.lab.auto.common.dto.core.ProductDiscountCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductDiscountCrudResp;

public interface ProductDiscountSvc {

    void getProductDiscount(ProductDiscountCrudReq crudReq, ProductDiscountCrudResp resp);

    void createProductDiscount(ProductDiscountCrudReq crudReq, ProductDiscountCrudResp resp);

    void updateProductDiscount(ProductDiscountCrudReq crudReq, ProductDiscountCrudResp resp);

    void deleteProductDiscounttById(ProductDiscountCrudReq crudReq, ProductDiscountCrudResp resp);

    void getAllProductDiscounts(ProductDiscountCrudReq crudReq, ProductDiscountCrudResp resp);
}
