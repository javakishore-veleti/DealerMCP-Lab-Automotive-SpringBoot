package com.dealer.mcp.lab.auto.common.svc;

import com.dealer.mcp.lab.auto.common.dto.core.AbstractCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.AbstractCrudResp;
import com.dealer.mcp.lab.auto.common.entity.core.AbstractCoreEntity;

public interface AppCrudSvc<T extends AbstractCoreEntity, R extends AbstractCrudReq<T>, O extends AbstractCrudResp<T>> {

    void getById(R crudReq, O resp);

    void create(R crudReq, O resp);

    void update(R crudReq, O resp);

    void delete(R crudReq, O resp);

    void getAll(R crudReq, O resp);


}
