package com.lixindi.gradproject.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lixindi on 2017/3/27.
 */
public class VoteSetting implements Serializable {
    private static final long serialVersionUID = -4904732969922228338L;
    private List<CandidateInfo> candidates;
    private String department;
    private String group;
    private String vote_type;
    private String ballot_type;
    private String round;
    private String times;
    private Integer advance_num;
    private Integer voter_num;

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

    public String getVote_type() {
        return vote_type;
    }

    public void setVote_type(String vote_type) {
        this.vote_type = vote_type;
    }

    public String getBallot_type() {
        return ballot_type;
    }

    public void setBallot_type(String ballot_type) {
        this.ballot_type = ballot_type;
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

    public Integer getVoter_num() {
        return voter_num;
    }

    public void setVoter_num(Integer voter_num) {
        this.voter_num = voter_num;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
