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
     * POST  /iframes : 创建一个新的iframe记录
     *
     * @param iframe the iframe to create
     * @return the ResponseEntity with status 201 (Created) and with body the new iframe, or with status 400 (Bad Request) if the iframe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/iframes")
    public ResponseEntity<Iframe> createIframe(@RequestBody(required = false) Iframe iframe) throws URISyntaxException {
        log.debug("REST request to save Iframe : {}", iframe);
        if (iframe.getId() != null) {
            throw new BadRequestAlertException("A new iframe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Iframe result = iframeService.save(iframe);
        return ResponseEntity.created(new URI("/api/iframes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /iframes : 更新一条以存在的iframe记录.
     *
     * @param iframe the iframe to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated iframe,
     * or with status 400 (Bad Request) if the iframe is not valid,
     * or with status 500 (Internal Server Error) if the iframe couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/iframes")
    public ResponseEntity<Iframe> updateIframe(@RequestBody(required = false) Iframe iframe) throws URISyntaxException {
        log.debug("REST request to update Iframe : {}", iframe);
        if (iframe.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Iframe result = iframeService.save(iframe);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, iframe.getId().toString()))
            .body(result);
    }

    /**
     * GET  /iframes : 获取所有iframe记录
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of iframes in body
     */
    @GetMapping("/iframes")
    public ResponseEntity<List<Iframe>> getAllIframes(Pageable pageable) {
        log.debug("REST request to get a page of Iframes");
        Page<Iframe> page = iframeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/iframes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /iframes/:id : get the "id" iframe. 通过id获取iframe记录
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
     * DELETE  /iframes/:id : delete the "id" iframe. 通过id删除iframe记录
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
}
