package com.dealer.mcp.lab.auto.common.dao.common;

import com.dealer.mcp.lab.auto.common.entity.core.ProductDiscountCore;

public interface ProductDiscountDaoCore<T extends ProductDiscountCore> {

    T getByName(String name);

    T getByCode(String code);

}
