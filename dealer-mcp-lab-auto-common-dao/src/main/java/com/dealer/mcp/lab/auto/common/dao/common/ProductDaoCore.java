package com.dealer.mcp.lab.auto.common.dao.common;

import com.dealer.mcp.lab.auto.common.entity.core.ProductCore;

public interface ProductDaoCore<T extends ProductCore> {
    T getByName(String name);

    T getByCode(String code);

}
