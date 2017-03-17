package com.lixindi.gradproject.dao;

import com.lixindi.gradproject.dto.CandidateDaoRequest;
import com.lixindi.gradproject.vo.CandidateInfo;
import com.lixindi.gradproject.vo.CandidateRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CandidateMapper {

    int insertCandidate(List<CandidateInfo> candidateInfos);

    List<CandidateInfo> getCandidate(CandidateDaoRequest candidateDaoRequest);

    List<String> getDepartment();

    int getTotal(CandidateDaoRequest candidateDaoRequest);

    int updateGroup(@Param("name") String name, @Param("department") String department, @Param("group") String group);

    List<String> getGroupByDepartment(@Param("department") String department);
}