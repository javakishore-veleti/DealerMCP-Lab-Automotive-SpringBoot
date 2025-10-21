package com.dealer.mcp.lab.auto.common.dao.common;

import com.dealer.mcp.lab.auto.common.entity.core.ProductFeatureCore;

public interface ProductFeatureDaoCore<T extends ProductFeatureCore> {

    T getByName(String name);

    T getByCode(String code);
}
