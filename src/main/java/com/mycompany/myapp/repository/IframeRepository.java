package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Iframe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Iframe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IframeRepository extends JpaRepository<Iframe, Long> {

    @Query("select name from Iframe group by name")
    List<String> findAllName();

    @Query("select time from Iframe group by time")
    List<String> findAllTime();

    @Query("select stage from Iframe group by stage")
    List<String> findAllStage();

    @Query("select group from Iframe group by group")
    List<String> findAllGroup();

    @Query("update Iframe i set i.name=?1,i.stage=?2,i.time=?3,i.group=?4,i.flag=?5,i.raceId=?6 where i.id = ?7")
    @Modifying
    void update(String name, String stage, String time, String group, Integer flag, Integer raceId, Long id);

    /**
     * 根据 iframe 的 name查询
     * @param name
     * @return
     */
    List<Iframe> findAllByName(String name);

    /**
     * 根据 iframe的name 和 阶段 来查询
     * @param stage
     * @return
     */
    List<Iframe> findAllByNameAndStage(String name, String stage);

    /**
     * 根据 iframe的name 和  时间 来查询
     * @param time
     * @return
     */
    List<Iframe> findAllByNameAndTime(String name, String time);

    /**
     * 根据 iframe的name 和 小组 来查询
     * @param group
     * @return
     */
    List<Iframe> findAllByNameAndGroup(String name, String group);

    /**
     * 根据 iframe的name 和 阶段 和 时间 来查询
     * @param name
     * @param stage
     * @param time
     * @return
     */
    List<Iframe> findAllByNameAndStageAndTime(String name, String stage, String time);

    /**
     * 根据 iframe的name 和 阶段 和 小组 来查询
     * @param name
     * @param stage
     * @param group
     * @return
     */
    List<Iframe> findAllByNameAndStageAndGroup(String name, String stage, String group);

    /**
     * 根据 iframe的name 和 时间 和 小组 来查询
     * @param name
     * @param time
     * @param group
     * @return
     */
    List<Iframe> findAllByNameAndTimeAndGroup(String name, String time, String group);

    /**
     * 根据 iframe的name 和 阶段 和 小组 和 时间 来查询
     * @param name
     * @param time
     * @param group
     * @param stage
     * @return
     */
    Iframe findAllByNameAndTimeAndGroupAndStage(String name, String time, String group, String stage);

    @Override
    void deleteAll();
}
