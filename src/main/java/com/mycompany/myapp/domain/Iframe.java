package com.mycompany.myapp.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Iframe.
 */
@Entity
@Table(name = "iframe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Iframe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;        // iframe名

    @Column(name = "stage")
    private String stage;       // 阶段

    @Column(name = "jhi_time")
    private String time;        // 时间

    @Column(name = "jhi_group")
    private String group;       // 小组

    @Column(name = "flag")
    private Integer flag;       // 是否设为默认项

    @Column(name = "race_id")
    private Integer raceId;     // 赛事id

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
           this.id = id;
    }

    public String getName() {
        return name;
    }

    public Iframe name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public Iframe stage(String stage) {
        this.stage = stage;
        return this;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getTime() {
        return time;
    }

    public Iframe time(String time) {
        this.time = time;
        return this;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGroup() {
        return group;
    }

    public Iframe group(String group) {
        this.group = group;
        return this;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getFlag() {
        return flag;
    }

    public Iframe flag(Integer flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getRaceId() {
        return raceId;
    }

    public Iframe raceId(Integer raceId) {
        this.raceId = raceId;
        return this;
    }

    public void setRaceId(Integer raceId) {
        this.raceId = raceId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public String toString() {
        return "Iframe{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", stage='" + getStage() + "'" +
            ", time='" + getTime() + "'" +
            ", group='" + getGroup() + "'" +
            ", flag=" + getFlag() +
            ", raceId=" + getRaceId() +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Iframe iframe = (Iframe) o;
        return name.equals(iframe.name) &&
            stage.equals(iframe.stage) &&
            time.equals(iframe.time) &&
            group.equals(iframe.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stage, time, group);
    }
}
