package com.dealer.mcp.lab.auto.common.dao.common;

import com.dealer.mcp.lab.auto.common.entity.core.ProductCtgCore;

public interface ProductCtgDaoCore<T extends ProductCtgCore> {

    T getByName(String name);

    T getByCode(String code);

}
