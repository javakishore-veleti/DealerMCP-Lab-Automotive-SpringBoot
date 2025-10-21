package com.dealer.mcp.lab.auto.common.dao.rdbms;

import com.dealer.mcp.lab.auto.common.dao.common.ProductCtgDaoCore;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductCtg;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCtgDao extends ProductCtgDaoCore<ProductCtg>, CrudRepository<ProductCtg, String> {
}
