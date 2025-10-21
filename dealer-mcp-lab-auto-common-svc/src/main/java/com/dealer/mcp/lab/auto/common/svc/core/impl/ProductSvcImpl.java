package com.dealer.mcp.lab.auto.common.svc.core.impl;

import com.dealer.mcp.lab.auto.common.dao.rdbms.ProductDao;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.Product;
import com.dealer.mcp.lab.auto.common.svc.core.ProductSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ProductSvcImpl implements ProductSvc {

    @Autowired
    private ProductDao productDao;

    @Override
    public void getProduct(ProductCrudReq crudReq, ProductCrudResp resp) {
        resp.setEntity(productDao.findById(crudReq.getId()).orElse(null));
    }

    @Override
    public void createProduct(ProductCrudReq crudReq, ProductCrudResp resp) {
        resp.setEntity(productDao.save(crudReq.getEntity()));
    }

    @Override
    public void updateProduct(ProductCrudReq crudReq, ProductCrudResp resp) {
        resp.setEntity(productDao.save(crudReq.getEntity()));
    }

    @Override
    public void deleteProductById(ProductCrudReq crudReq, ProductCrudResp resp) {
        this.getProduct(crudReq, resp);
        this.productDao.deleteById(crudReq.getEntity().getId());
    }

    @Override
    public void getAllProducts(ProductCrudReq crudReq, ProductCrudResp resp) {
        List<Product> list = StreamSupport.stream(this.productDao.findAll().spliterator(), false)
                .toList();
        resp.setEntityList(list);
    }
}
