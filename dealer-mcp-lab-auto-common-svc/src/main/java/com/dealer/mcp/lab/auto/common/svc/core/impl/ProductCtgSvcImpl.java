package com.dealer.mcp.lab.auto.common.svc.core.impl;

import com.dealer.mcp.lab.auto.common.dao.rdbms.ProductCtgDao;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCtgCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductCtgCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductCtg;
import com.dealer.mcp.lab.auto.common.svc.core.ProductCtgSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@SuppressWarnings("ALL")
@Service
public class ProductCtgSvcImpl implements ProductCtgSvc {

    @Autowired
    private ProductCtgDao productCtgDao;

    @Override
    public void getProductCtg(ProductCtgCrudReq crudReq, ProductCtgCrudResp resp) {
        resp.setEntity(productCtgDao.save(crudReq.getEntity()));
    }

    @Override
    public void createProductCtg(ProductCtgCrudReq crudReq, ProductCtgCrudResp resp) {
        resp.setEntity(productCtgDao.save(crudReq.getEntity()));
    }

    @Override
    public void updateProductCtg(ProductCtgCrudReq crudReq, ProductCtgCrudResp resp) {
        resp.setEntity(productCtgDao.save(crudReq.getEntity()));
    }

    @Override
    public void deleteProductCtgById(ProductCtgCrudReq crudReq, ProductCtgCrudResp resp) {
        this.getProductCtg(crudReq, resp);
        this.productCtgDao.deleteById(resp.getId());
    }

    @Override
    public void getAllProductCategories(ProductCtgCrudReq crudReq, ProductCtgCrudResp resp) {
        List<ProductCtg> list = StreamSupport.stream(this.productCtgDao.findAll().spliterator(), false)
                .toList();
        resp.setEntityList(list);    }
}
