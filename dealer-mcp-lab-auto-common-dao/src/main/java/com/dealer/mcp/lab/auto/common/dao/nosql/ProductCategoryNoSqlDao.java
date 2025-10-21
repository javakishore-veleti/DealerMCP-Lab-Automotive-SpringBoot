package com.dealer.mcp.lab.auto.common.dao.nosql;

import com.dealer.mcp.lab.auto.common.dao.common.ProductCtgDaoCore;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductCtg;
import org.springframework.data.repository.Repository;

public interface ProductCategoryNoSqlDao extends ProductCtgDaoCore<ProductCtg>, Repository<ProductCtg, String> {
}
