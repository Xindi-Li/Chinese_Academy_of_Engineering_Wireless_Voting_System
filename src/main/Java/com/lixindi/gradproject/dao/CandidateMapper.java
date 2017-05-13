package com.lixindi.gradproject.dao;

import com.lixindi.gradproject.dto.CandidateDaoRequest;
import com.lixindi.gradproject.vo.CandidateInfo;
import com.lixindi.gradproject.vo.NomineeRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CandidateMapper {

    int insertCandidate(List<CandidateInfo> candidateInfos);

    List<CandidateInfo> getCandidate(CandidateDaoRequest candidateDaoRequest);

    List<CandidateInfo> getNominee(NomineeRequest nomineeRequest);

    List<String> getDepartment(@Param("mode") Boolean mode);

    int getTotal(CandidateDaoRequest candidateDaoRequest);

    int updateGroup(@Param("name") String name, @Param("department") String department, @Param("group") String group);

    List<String> getGroupByDepartment(@Param("department") String department, @Param("mode") Boolean mode);

    int updateCandidate(CandidateInfo candidateInfo);

    int deleteCandidate(CandidateInfo candidateInfo);

    int markCandidate(List<String> candidates);
}