package com.lixindi.gradproject.dao;

import com.lixindi.gradproject.dto.CandidateDaoRequest;
import com.lixindi.gradproject.vo.CandidateInfo;
import com.lixindi.gradproject.vo.CandidateRequest;

import java.util.List;

public interface CandidateMapper {

    int insertCandidate(List<CandidateInfo> candidateInfos);

    List<CandidateInfo> getCandidate(CandidateDaoRequest candidateDaoRequest);

    List<String> getDepartment();

    int getTotal(String department);

    int updateGroup(String name, String department, String group);
}