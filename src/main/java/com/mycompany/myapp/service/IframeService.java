package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Iframe;
import com.mycompany.myapp.repository.IframeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Iframe.
 */
@Service
@Transactional
public class IframeService {

    private final Logger log = LoggerFactory.getLogger(IframeService.class);

    private final IframeRepository iframeRepository;

    public IframeService(IframeRepository iframeRepository) {
        this.iframeRepository = iframeRepository;
    }

    /**
     * Save a iframe.
     *
     * @param iframe the entity to save
     * @return the persisted entity
     */
    public Iframe save(Iframe iframe) {
        log.debug("Request to save Iframe : {}", iframe);
        return iframeRepository.save(iframe);
    }

    /**
     * Get all the iframes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Iframe> findAll(Pageable pageable) {
        log.debug("Request to get all Iframes");
        return iframeRepository.findAll(pageable);
    }


    /**
     * Get one iframe by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<Iframe> findOne(Long id) {
        log.debug("Request to get Iframe : {}", id);
        return iframeRepository.findById(id);
    }

    /**
     * Delete the iframe by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Iframe : {}", id);
        iframeRepository.deleteById(id);
    }
}
