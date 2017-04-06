package com.lixindi.gradproject.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lixindi on 2017/3/29.
 */
public class VoteResult implements Serializable {
    private static final long serialVersionUID = -2359442457509883970L;
    private Integer voterID;
    private List<CandidateInfo> candidates;
    private String department;
    private Integer advance_num;

    public Integer getVoterID() {
        return voterID;
    }

    public void setVoterID(Integer voterID) {
        this.voterID = voterID;
    }

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

    public Integer getAdvance_num() {
        return advance_num;
    }

    public void setAdvance_num(Integer advance_num) {
        this.advance_num = advance_num;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
