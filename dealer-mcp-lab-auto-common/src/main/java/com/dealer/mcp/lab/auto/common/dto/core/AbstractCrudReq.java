package com.dealer.mcp.lab.auto.common.dto.core;

import com.dealer.mcp.lab.auto.common.entity.core.AbstractCoreEntity;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public abstract class AbstractCrudReq<T extends AbstractCoreEntity> {

    private String operation;
    private String id;
    private List<String> ids;
    private T entity;
    private List<T> entityList;
    private Map<String, Object> ctxData;
}
