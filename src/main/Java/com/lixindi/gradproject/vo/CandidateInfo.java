package com.lixindi.gradproject.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by lixindi on 2017/2/24.
 */
public class CandidateInfo implements Serializable {
    private static final long serialVersionUID = -5590067601672181854L;
    private String candidate_num;
    private String name;
    private Integer age;
    private String major;
    private String company;
    private String department;
    private String group;
    private Integer score;
    private Boolean is_advance;

    public String getCandidate_num() {
        return candidate_num;
    }

    public void setCandidate_num(String candidate_num) {
        this.candidate_num = candidate_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getIs_advance() {
        return is_advance;
    }

    public void setIs_advance(Boolean is_advance) {
        this.is_advance = is_advance;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
