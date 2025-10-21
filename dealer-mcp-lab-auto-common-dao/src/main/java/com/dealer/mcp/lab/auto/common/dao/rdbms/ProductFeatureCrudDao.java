package com.dealer.mcp.lab.auto.common.dao.rdbms;

import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductFeature;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFeatureCrudDao extends AppCrudDao<ProductFeature> {

    List<ProductFeature> findByProductId(String productId);
}
