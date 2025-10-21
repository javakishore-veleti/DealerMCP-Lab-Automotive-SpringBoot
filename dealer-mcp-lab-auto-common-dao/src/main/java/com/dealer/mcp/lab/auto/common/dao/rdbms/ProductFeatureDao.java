package com.dealer.mcp.lab.auto.common.dao.rdbms;

import com.dealer.mcp.lab.auto.common.dao.common.ProductFeatureDaoCore;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductFeature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFeatureDao extends ProductFeatureDaoCore<ProductFeature>, CrudRepository<ProductFeature, String> {
}
