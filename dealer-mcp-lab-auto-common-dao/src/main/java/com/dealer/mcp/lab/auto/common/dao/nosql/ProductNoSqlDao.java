package com.dealer.mcp.lab.auto.common.dao.nosql;

import com.dealer.mcp.lab.auto.common.entity.nosql.ProductNoSql;
import org.springframework.data.repository.Repository;

public interface ProductNoSqlDao extends Repository<ProductNoSql, String> {
}
