package com.dealer.mcp.lab.auto.common.dao.rdbms;

import com.dealer.mcp.lab.auto.common.dao.common.ProductDaoCore;
import com.dealer.mcp.lab.auto.common.entity.rdbms.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends ProductDaoCore<Product>, CrudRepository<Product, String> {
}
