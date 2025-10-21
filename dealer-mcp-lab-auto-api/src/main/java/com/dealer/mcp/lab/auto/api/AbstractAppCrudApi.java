package com.dealer.mcp.lab.auto.api;

import com.dealer.mcp.lab.auto.common.dto.core.AbstractCrudReq;
import com.dealer.mcp.lab.auto.common.dto.core.AbstractCrudResp;
import com.dealer.mcp.lab.auto.common.entity.core.AbstractCoreEntity;
import com.dealer.mcp.lab.auto.common.svc.AppCrudSvc;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Data
public abstract class AbstractAppCrudApi<T extends AbstractCoreEntity, R extends AbstractCrudReq<T>, O extends AbstractCrudResp<T>, S extends AppCrudSvc<T, R, O>> {

    private final S crudSvc;
    private final Class<R> reqClass;
    private final Class<O> respClass;

    public AbstractAppCrudApi(S crudSvc, Class<R> reqClass, Class<O> respClass) {
        this.crudSvc = crudSvc;
        this.reqClass = reqClass;
        this.respClass = respClass;
    }

    protected O newInstance() {
        try {
            return respClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(path = "/get-by-id")
    public ResponseEntity<O> getById(@RequestBody R crudReq) {
        log.info("STARTED getById");

        StopWatch sw = new StopWatch();
        sw.start();

        O crudResp = null;
        try {
            crudResp = newInstance();
            this.crudSvc.getById(crudReq, crudResp);
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
        }

        sw.stop();

        log.info("COMPLETED getById TimeTaken {}", sw.getTotalTimeMillis());
        return ResponseEntity.ok(crudResp);
    }

    @RequestMapping(path = "/create")
    public ResponseEntity<O> create(@RequestBody R crudReq) {
        log.info("STARTED create");

        StopWatch sw = new StopWatch();
        sw.start();

        O crudResp = null;
        try {
            crudResp = newInstance();
            this.crudSvc.create(crudReq, crudResp);
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
        }

        sw.stop();

        log.info("COMPLETED create TimeTaken {}", sw.getTotalTimeMillis());
        return ResponseEntity.ok(crudResp);
    }

    @RequestMapping(path = "/update")
    public ResponseEntity<O> update(@RequestBody R crudReq) {
        log.info("STARTED update");

        StopWatch sw = new StopWatch();
        sw.start();

        O crudResp = null;
        try {
            crudResp = newInstance();
            this.crudSvc.update(crudReq, crudResp);
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
        }

        sw.stop();

        log.info("COMPLETED update TimeTaken {}", sw.getTotalTimeMillis());
        return ResponseEntity.ok(crudResp);
    }

    @RequestMapping(path = "/delete")
    public ResponseEntity<O> delete(@RequestBody R crudReq) {
        log.info("STARTED delete");

        StopWatch sw = new StopWatch();
        sw.start();

        O crudResp = null;
        try {
            crudResp = newInstance();
            this.crudSvc.delete(crudReq, crudResp);
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
        }

        sw.stop();

        log.info("COMPLETED delete TimeTaken {}", sw.getTotalTimeMillis());
        return ResponseEntity.ok(crudResp);
    }

    @RequestMapping(path = "/get-all")
    public ResponseEntity<O> getAll(@RequestBody R crudReq) {
        log.info("STARTED getAll");

        StopWatch sw = new StopWatch();
        sw.start();

        O crudResp = null;
        try {
            crudResp = newInstance();
            this.crudSvc.getAll(crudReq, crudResp);
        } catch (Throwable throwable) {
            log.error(throwable.getMessage(), throwable);
        }

        sw.stop();

        log.info("COMPLETED getAll TimeTaken {}", sw.getTotalTimeMillis());
        return ResponseEntity.ok(crudResp);
    }
}
