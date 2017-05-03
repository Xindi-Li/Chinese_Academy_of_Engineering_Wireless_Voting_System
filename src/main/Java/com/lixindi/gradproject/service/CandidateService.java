package com.lixindi.gradproject.service;

import com.lixindi.gradproject.dto.CandidateResponse;
import com.lixindi.gradproject.dto.ServiceResponse;
import com.lixindi.gradproject.vo.CandidateInfo;
import com.lixindi.gradproject.vo.CandidateRequest;
import com.lixindi.gradproject.vo.GroupRequest;
import com.lixindi.gradproject.vo.NomineeRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by lixindi on 2017/3/3.
 */
public interface CandidateService {
    ServiceResponse<Boolean> insertCandidate(List<CandidateInfo> candidateInfos);

    CandidateResponse getCandidate(CandidateRequest candidateRequest);

    List<CandidateInfo> getNominee(NomineeRequest request);

    List<String> getDepartment();

    Boolean groupCandidate(GroupRequest groupRequest);

    List<String> getGroupByDepartment(String department);

    Boolean updateCandidate(CandidateInfo candidateInfo);

    Boolean deleteCandidate(CandidateInfo candidateInfo);

    void markCandidate(List<String> candidates);

    void clearRedis();
}
