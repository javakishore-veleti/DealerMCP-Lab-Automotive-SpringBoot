package com.dealer.mcp.lab.auto.common.svc.core.impl;

import com.dealer.mcp.lab.auto.common.dao.rdbms.ProductDiscountDao;
import com.dealer.mcp.lab.auto.common.dto.core.ProductDiscountCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.ProductDiscountCrudResp;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductDiscount;
import com.dealer.mcp.lab.auto.common.svc.core.ProductDiscountSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
public class ProductDiscountSvcImpl implements ProductDiscountSvc {

    @Autowired
    private ProductDiscountDao productDiscountDao;

    @Override
    public void getProductDiscount(ProductDiscountCrudReq crudReq, ProductDiscountCrudResp resp) {
        resp.setEntity(productDiscountDao.findById(crudReq.getId()).orElse(null));
    }

    @Override
    public void createProductDiscount(ProductDiscountCrudReq crudReq, ProductDiscountCrudResp resp) {
        resp.setEntity(productDiscountDao.save(crudReq.getEntity()));
    }

    @Override
    public void updateProductDiscount(ProductDiscountCrudReq crudReq, ProductDiscountCrudResp resp) {
        resp.setEntity(productDiscountDao.save(crudReq.getEntity()));
    }

    @Override
    public void deleteProductDiscounttById(ProductDiscountCrudReq crudReq, ProductDiscountCrudResp resp) {
        this.getProductDiscount(crudReq, resp);
        this.productDiscountDao.deleteById(resp.getEntity().getId());
    }

    @Override
    public void getAllProductDiscounts(ProductDiscountCrudReq crudReq, ProductDiscountCrudResp resp) {
        List<ProductDiscount> list = StreamSupport.stream(this.productDiscountDao.findAll().spliterator(), false)
                .toList();
        resp.setEntityList(list);
    }
}
