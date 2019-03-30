package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Iframe;
import com.mycompany.myapp.repository.IframeRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
     * Save some iframes.
     *
     * @param iframes some entities to save
     * @return the persisted entities
     */
    public List<Iframe> saveAll(List<Iframe> iframes) {
        log.debug("Request to save Iframe : {}", iframes);
        return iframeRepository.saveAll(iframes);
    }

    /**
     * update a iframe
     *
     * @param data
     * @return
     */
    public Iframe update(Iframe data) {
        log.debug("Request to update Iframe : {}", data);
        return iframeRepository.findById(data.getId())
            .map(iframe -> {
                iframe.setName(data.getName());
                iframe.setGroup(data.getGroup());
                iframe.setRaceId(data.getRaceId());
                iframe.setStage(data.getStage());
                iframe.setTime(data.getTime());
                iframe.setFlag(data.getFlag());
                return iframeRepository.save(iframe);
            })
            .orElseThrow(() -> new BadRequestAlertException("id 错误", null, null));
    }

    /**
     * Get all the iframes.
     *
     * @param
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Iframe> findAll() {
        log.debug("Request to get all Iframes");
        return iframeRepository.findAll();
    }

    public List<String> findAllName(){
        log.debug("Request to get all Iframes names");
        return iframeRepository.findAllName();
    }

    public List<String> findAllTime(){
        log.debug("Request to get all Iframes Times");
        return iframeRepository.findAllTime();
    }

    public List<String> findAllStage(){
        log.debug("Request to get all Iframes stages");
        return iframeRepository.findAllStage();
    }

    public List<String> findAllGroup(){
        log.debug("Request to get all Iframes stages");
        return iframeRepository.findAllGroup();
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

    /**
     * 根据 iframe 的 name查询
     *
     * @param name
     * @return
     */
    public List<Iframe> findAllByName(String name) {
        return iframeRepository.findAllByName(name);
    }

    /**
     * 根据 iframe的name 和 阶段 来查询
     *
     * @param stage
     * @return
     */
    public List<Iframe> findAllByNameAndStage(String name, String stage) {
        return iframeRepository.findAllByNameAndStage(name, stage);
    }

    /**
     * 根据 iframe的name 和  时间 来查询
     *
     * @param time
     * @return
     */
    public List<Iframe> findAllByNameAndTime(String name, String time) {
        return iframeRepository.findAllByNameAndTime(name, time);
    }

    /**
     * 根据 iframe的name 和 小组 来查询
     *
     * @param group
     * @return
     */
    public List<Iframe> findAllByNameAndGroup(String name, String group) {
        return iframeRepository.findAllByNameAndGroup(name, group);
    }

    /**
     * 根据 iframe的name 和 阶段 和 时间 来查询
     *
     * @param name
     * @param stage
     * @return
     */
    public List<Iframe> findAllByNameAndStageAndTime(String name, String stage, String time) {
        return iframeRepository.findAllByNameAndStageAndTime(name, stage, time);
    }

    /**
     * 根据 iframe的name 和 阶段 和 小组 来查询
     *
     * @param name
     * @param stage
     * @param group
     * @return
     */
    public List<Iframe> findAllByNameAndStageAndGroup(String name, String stage, String group) {
        return iframeRepository.findAllByNameAndStageAndGroup(name, stage, group);
    }

    /**
     * 根据 iframe的name 和 时间 和 小组 来查询
     *
     * @param name
     * @param time
     * @param group
     * @return
     */
    public List<Iframe> findAllByNameAndTimeAndGroup(String name, String time, String group) {
        return iframeRepository.findAllByNameAndTimeAndGroup(name, time, group);
    }

    /**
     * 根据 iframe的name 和 阶段 和 小组 和 时间 来查询
     *
     * @param name
     * @param time
     * @param group
     * @param stage
     * @return
     */
    public Iframe findAllByNameAndTimeAndGroupAndStage(String name, String time, String group, String stage){
        return iframeRepository.findAllByNameAndTimeAndGroupAndStage(name, time, group, stage);
    }

}
