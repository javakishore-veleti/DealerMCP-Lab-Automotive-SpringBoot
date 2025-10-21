package com.dealer.mcp.lab.auto.common.svc.mcp.impl;

import com.dealer.mcp.lab.auto.common.dto.core.*;
import com.dealer.mcp.lab.auto.common.entity.rdbms.Product;
import com.dealer.mcp.lab.auto.common.svc.core.ProductCrudSvc;
import com.dealer.mcp.lab.auto.common.svc.core.ProductCtgCrudSvc;
import com.dealer.mcp.lab.auto.common.svc.core.ProductFeatureCrudSvc;
import com.dealer.mcp.lab.auto.common.svc.mcp.ProductContextSvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class ProductContextSvcImpl implements ProductContextSvc {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ProductCrudSvc productCrudSvc;

    @Autowired
    private ProductCtgCrudSvc productCtgCrudSvc;

    @Autowired
    private ProductFeatureCrudSvc productFeatureCrudSvc;

    /**
     * Builds a contextual view of a product entity.
     * Cached by product ID for fast reuse.
     */
    @Override
    @Cacheable("productContext")
    public ObjectNode buildProductContext(String productId) {
        ObjectNode node = mapper.createObjectNode();

        ProductCrudReq crudReq = new ProductCrudReq();
        crudReq.setId(productId);
        ProductCrudResp crudResp = new ProductCrudResp();

        productCrudSvc.getById(crudReq, crudResp);
        Product product = crudResp.getEntity();

        if (ObjectUtils.isEmpty(product)) {
            return node.put("error", "Product not found");
        }

        node.put("id", product.getId());
        node.put("name", product.getName());

        // Add category
        ProductCtgCrudReq ctgCrudReq = new ProductCtgCrudReq();
        ctgCrudReq.setId(product.getProductCtgId());
        ProductCtgCrudResp ctgCrudResp = new ProductCtgCrudResp();

        productCtgCrudSvc.getById(ctgCrudReq, ctgCrudResp);
        if(!ObjectUtils.isEmpty(ctgCrudResp.getEntity())) {
            node.put("category", ctgCrudResp.getEntity().getName());
        }

        // Add features
        ProductFeatureCrudReq productFeatCrudReq = new ProductFeatureCrudReq();
        productFeatCrudReq.setId(product.getProductCtgId());
        ProductFeatureCrudResp productFeatCrudResp = new ProductFeatureCrudResp();

        productFeatureCrudSvc.getFeaturesByProductId(productFeatCrudReq, productFeatCrudResp);
        if(!ObjectUtils.isEmpty(productFeatCrudResp.getEntityList())) {
            node.set("features", mapper.valueToTree(productFeatCrudResp.getEntityList()));
        }

        // Add discounts

        return node;
    }
}
