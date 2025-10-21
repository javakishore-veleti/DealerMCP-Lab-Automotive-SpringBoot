package com.dealer.mcp.lab.auto.common.svc.core;

import com.dealer.mcp.lab.auto.common.dto.core.ProductCtgCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCtgCrudResp;

public interface ProductCtgSvc {

    void getProductCtg(ProductCtgCrudReq crudReq, ProductCtgCrudResp resp);

    void createProductCtg(ProductCtgCrudReq crudReq, ProductCtgCrudResp resp);

    void updateProductCtg(ProductCtgCrudReq crudReq, ProductCtgCrudResp resp);

    void deleteProductCtgById(ProductCtgCrudReq crudReq, ProductCtgCrudResp resp);

    void getAllProductCategories(ProductCtgCrudReq crudReq, ProductCtgCrudResp resp);
}
