package com.dealer.mcp.lab.auto.common.dao.rdbms;

import com.dealer.mcp.lab.auto.common.entity.rdbms.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCrudDao extends AppCrudDao<Product> {
}
