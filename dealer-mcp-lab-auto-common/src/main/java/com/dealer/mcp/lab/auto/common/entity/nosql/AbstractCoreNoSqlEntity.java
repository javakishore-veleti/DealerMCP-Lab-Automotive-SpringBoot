package com.dealer.mcp.lab.auto.common.entity.nosql;

import com.dealer.mcp.lab.auto.common.entity.core.AbstractCoreEntity;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public interface AbstractCoreNoSqlEntity<T extends AbstractCoreEntity> {

    default T getEntity() {
        return null;
    }

    default void setEntity(T entity) {}
}
