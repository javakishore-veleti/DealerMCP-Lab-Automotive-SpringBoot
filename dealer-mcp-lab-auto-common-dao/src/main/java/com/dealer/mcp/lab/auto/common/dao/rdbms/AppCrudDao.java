package com.dealer.mcp.lab.auto.common.dao.rdbms;

import com.dealer.mcp.lab.auto.common.entity.core.AbstractCoreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppCrudDao<T extends AbstractCoreEntity> extends CrudRepository<T, String> {
}
