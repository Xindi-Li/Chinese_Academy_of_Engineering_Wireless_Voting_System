package com.lixindi.gradproject.dao;

import com.lixindi.gradproject.vo.CandidateInfo;
import com.lixindi.gradproject.vo.CandidateRequest;

import java.util.List;

public interface CandidateMapper {

    int insertCandidate(List<CandidateInfo> candidateInfos);

    List<CandidateInfo> getCandidate(CandidateRequest candidateRequest);
}