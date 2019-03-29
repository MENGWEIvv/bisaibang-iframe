package com.mycompany.myapp.web.rest;
import com.mycompany.myapp.domain.Iframe;
import com.mycompany.myapp.service.IframeService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Iframe.
 */
@RestController
@RequestMapping("/api")
public class IframeResource {

    private final Logger log = LoggerFactory.getLogger(IframeResource.class);

    private static final String ENTITY_NAME = "iframe";

    private final IframeService iframeService;

    public IframeResource(IframeService iframeService) {
        this.iframeService = iframeService;
    }

    /**
     * POST  /iframes : Create some new iframes.
     *
     * @param iframes the iframe to create
     * @return the ResponseEntity with status 201 (Created) and with body the new iframe, or with status 400 (Bad Request) if the iframe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/iframes")
    public List<Iframe> createIframe(@RequestBody List<Iframe> iframes) throws URISyntaxException {
        log.debug("REST request to save Iframe : {}", iframes);
        for (Iframe iframe: iframes) {
            if (iframe.getId() != null) {
                throw new BadRequestAlertException("A new iframe cannot already have an ID", ENTITY_NAME, "idexists");
            }
        }

        return iframeService.saveAll(iframes);
    }

    /**
     * PUT  /iframes : Update an existing iframe.
     *
     * @param iframe the iframe to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated iframe,
     * or with status 400 (Bad Request) if the iframe is not valid,
     * or with status 500 (Internal Server Error) if the iframe couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/iframes")
    public Iframe updateIframe(@RequestBody Iframe iframe) throws URISyntaxException {
        log.debug("REST request to update Iframe : {}", iframe);
        if (iframe.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        return iframeService.update(iframe);
    }

    /**
     * GET  /iframes : get all the iframes.
     *
     * @param
     * @return the ResponseEntity with status 200 (OK) and the list of iframes in body
     */
    @GetMapping("/iframes")
    public List<Iframe> getAllIframes() {
        log.debug("REST request to get a page of Iframes");
        return iframeService.findAll();
    }

    /**
     * GET  /iframes/:id : get the "id" iframe.
     *
     * @param id the id of the iframe to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the iframe, or with status 404 (Not Found)
     */
    @GetMapping("/iframes/{id}")
    public ResponseEntity<Iframe> getIframe(@PathVariable Long id) {
        log.debug("REST request to get Iframe : {}", id);
        Optional<Iframe> iframe = iframeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(iframe);
    }

    /**
     * DELETE  /iframes/:id : delete the "id" iframe.
     *
     * @param id the id of the iframe to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/iframes/{id}")
    public ResponseEntity<Void> deleteIframe(@PathVariable Long id) {
        log.debug("REST request to delete Iframe : {}", id);
        iframeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * 根据 iframe 的 name查询
     * @param name
     * @return
     */
    @GetMapping("/findAllByName/{name}")
    public List<Iframe> findAllByName(@PathVariable String name){
        log.debug("REST request to Get Iframe ByName : {}", name);
        return iframeService.findAllByName(name);
    }

    /**
     * 根据 iframe的name 和 阶段 来查询
     * @param stage
     * @return
     */
    @GetMapping("/findAllByNameAndStage/{name}/{stage}")
    public List<Iframe> findAllByNameAndStage(@PathVariable String name, @PathVariable String stage){
        log.debug("REST request to Get Iframe ByNameAndStage : {}", name + "---" + stage);
        return iframeService.findAllByNameAndStage(name, stage);
    }

    /**
     * 根据 iframe的name 和  时间 来查询
     * @param time
     * @return
     */
    @GetMapping("/findAllByNameAndTime/{name}/{time}")
    public List<Iframe> findAllByNameAndTime(@PathVariable String name, @PathVariable String time){
        log.debug("REST request to Get Iframe ByNameAndTime : {}", name + "---" + time);
        return iframeService.findAllByNameAndTime(name, time);
    }

    /**
     * 根据 iframe的name 和 小组 来查询
     * @param group
     * @return
     */
    @GetMapping("/findAllByNameAndGroup/{name}/{group}")
    public List<Iframe> findAllByNameAndGroup(@PathVariable String name, @PathVariable String group){
        log.debug("REST request to Get Iframe ByNameAndGroup : {}", name + "---" + group);
        return iframeService.findAllByNameAndGroup(name, group);
    }

    /**
     * 根据 iframe的name 和 阶段 和 时间 来查询
     * @param name
     * @param stage
     * @param time
     * @return
     */
    @GetMapping("/findAllByNameAndStageAndTime/{name}/{stage}/{time}")
    public List<Iframe> findAllByNameAndStageAndTime(@PathVariable String name, @PathVariable String stage, @PathVariable String time){
        log.debug("REST request to Get Iframe ByNameAndGroup : {}", name + "---" + stage + "---" + time);
        return iframeService.findAllByNameAndStageAndTime(name, stage, time);
    }

    /**
     * 根据 iframe的name 和 阶段 和 小组 来查询
     * @param name
     * @param stage
     * @param group
     * @return
     */
    @GetMapping("/findAllByNameAndStageAndGroup/{name}/{stage}/{group}")
    public List<Iframe> findAllByNameAndStageAndGroup(@PathVariable String name, @PathVariable String stage, @PathVariable String group){
        log.debug("REST request to Get Iframe ByNameAndStageAndGroup : {}", name + "---" + stage + "---" + group);
        return iframeService.findAllByNameAndStageAndGroup(name, stage, group);
    }

    /**
     * 根据 iframe的name 和 时间 和 小组 来查询
     * @param name
     * @param time
     * @param group
     * @return
     */
    @GetMapping("/findAllByNameAndTimeAndGroup/{name}/{time}/{group}")
    public List<Iframe> findAllByNameAndTimeAndGroup(@PathVariable String name, @PathVariable String time, @PathVariable String group){
        log.debug("REST request to Get Iframe ByNameAndTimeAndGroup : {}", name + "---" + time + "---" + group);
        return iframeService.findAllByNameAndTimeAndGroup(name, time, group);
    }

    /**
     * 根据 iframe的name 和 阶段 和 小组 和 时间 来查询
     * @param name
     * @param time
     * @param group
     * @param stage
     * @return
     */
    @GetMapping("/findAllByNameAndTimeAndGroupAndStage/{name}/{time}/{group}/{stage}")
    public List<Iframe> findAllByNameAndTimeAndGroupAndStage(@PathVariable String name, @PathVariable String time, @PathVariable String group, @PathVariable String stage){
        log.debug("REST request to Get Iframe ByNameAndTimeAndGroupAndStage : {}", name + "---" + time + "---" + group + "---" + stage);
        return iframeService.findAllByNameAndTimeAndGroupAndStage(name,time,group,stage);
    }

}
