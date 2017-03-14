package com.lixindi.gradproject.dto;

import com.lixindi.gradproject.vo.CandidateInfo;

import java.util.List;

/**
 * Created by lixindi on 2017/3/14.
 */
public class CandidateResponse {
    private List<CandidateInfo> candidateInfos;
    private int total;

    public List<CandidateInfo> getCandidateInfos() {
        return candidateInfos;
    }

    public void setCandidateInfos(List<CandidateInfo> candidateInfos) {
        this.candidateInfos = candidateInfos;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
