package com.dealer.mcp.lab.auto.common.dao.rdbms;

import com.dealer.mcp.lab.auto.common.dao.common.ProductDiscountDaoCore;
import com.dealer.mcp.lab.auto.common.entity.rdbms.ProductDiscount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDiscountDao extends AppCrudDao<ProductDiscount> {
}
