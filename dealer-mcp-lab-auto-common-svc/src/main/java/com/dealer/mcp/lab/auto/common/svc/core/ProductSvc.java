package com.dealer.mcp.lab.auto.common.svc.core;

import com.dealer.mcp.lab.auto.common.dto.core.ProductCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCrudResp;

public interface ProductSvc {

    void getProduct(ProductCrudReq crudReq, ProductCrudResp resp);

    void createProduct(ProductCrudReq crudReq, ProductCrudResp resp);

    void updateProduct(ProductCrudReq crudReq, ProductCrudResp resp);

    void deleteProductById(ProductCrudReq crudReq, ProductCrudResp resp);

    void getAllProducts(ProductCrudReq crudReq, ProductCrudResp resp);
}
