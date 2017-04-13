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
    private Integer advance_score;
    private List<CandidateInfo> candidates;

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

    public Integer getAdvance_score() {
        return advance_score;
    }

    public void setAdvance_score(Integer advance_score) {
        this.advance_score = advance_score;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
