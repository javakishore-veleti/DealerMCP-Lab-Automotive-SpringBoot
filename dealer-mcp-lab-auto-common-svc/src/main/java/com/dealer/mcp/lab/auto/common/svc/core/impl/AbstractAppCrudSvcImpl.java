package com.dealer.mcp.lab.auto.common.svc.core.impl;

import com.dealer.mcp.lab.auto.common.dao.rdbms.AppCrudDao;
import com.dealer.mcp.lab.auto.common.dto.core.AbstractCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.AbstractCrudResp;
import com.dealer.mcp.lab.auto.common.entity.core.AbstractCoreEntity;
import com.dealer.mcp.lab.auto.common.svc.AppCrudSvc;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.StreamSupport;

@SuppressWarnings("ALL")
@Data
@Slf4j
public class AbstractAppCrudSvcImpl<T extends AbstractCoreEntity, R extends AbstractCrudReq<T>, O extends AbstractCrudResp<T>, E extends AppCrudDao<T>>  implements AppCrudSvc<T, R, O> {

    private E crudDao;

    public AbstractAppCrudSvcImpl(E crudDao) {
        this.crudDao = crudDao;
    }

    @Override
    public void getById(R crudReq, O resp) {
        log.info("STARTED getById");

        resp.setEntity(crudDao.findById(crudReq.getId()).orElse(null));

        log.info("COMPLETED getById");
    }

    @Override
    public void create(R crudReq, O resp) {
        log.info("STARTED create");

        resp.setEntity(crudDao.save(crudReq.getEntity()));

        log.info("COMPLETED create");
    }

    @Override
    public void update(R crudReq, O resp) {
        log.info("STARTED update");

        resp.setEntity(crudDao.save(crudReq.getEntity()));

        log.info("COMPLETED update");
    }

    @Override
    public void delete(R crudReq, O resp) {
        log.info("STARTED delete");

        this.getById(crudReq,resp);
        crudDao.deleteById(crudReq.getEntity().getId());

        log.info("COMPLETED delete");
    }

    @Override
    public void getAll(R crudReq, O resp) {
        log.info("STARTED getAll");

        List<T> list = StreamSupport.stream(this.crudDao.findAll().spliterator(), false)
                .toList();
        resp.setEntityList(list);

        log.info("COMPLETED getAll");
    }
}
