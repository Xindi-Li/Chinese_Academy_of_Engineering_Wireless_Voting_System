package com.lixindi.gradproject.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lixindi on 2017/3/29.
 */
public class VoteResult implements Serializable {
    private static final long serialVersionUID = 1301859431868093469L;
    private List<CandidateInfo> candidates;
    private String department;
    private String group;
    private String round;
    private String times;
    private Integer advance_num;

    public List<CandidateInfo> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<CandidateInfo> candidates) {
        this.candidates = candidates;
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

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Integer getAdvance_num() {
        return advance_num;
    }

    public void setAdvance_num(Integer advance_num) {
        this.advance_num = advance_num;
    }
}
