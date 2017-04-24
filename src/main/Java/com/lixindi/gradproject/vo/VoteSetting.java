package com.lixindi.gradproject.vo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by lixindi on 2017/3/27.
 */
public class VoteSetting implements Serializable {
    private static final long serialVersionUID = -4904732969922228338L;
    private String title;
    private String ballot_type;
    private Integer advance_num;
    private Integer voter_num;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBallot_type() {
        return ballot_type;
    }

    public void setBallot_type(String ballot_type) {
        this.ballot_type = ballot_type;
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
