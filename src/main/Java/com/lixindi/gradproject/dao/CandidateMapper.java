package com.lixindi.gradproject.dao;

import com.lixindi.gradproject.vo.CandidateInfo;

import java.util.List;

public interface CandidateMapper {

    int insertCandidate(List<CandidateInfo> candidateInfos);
}