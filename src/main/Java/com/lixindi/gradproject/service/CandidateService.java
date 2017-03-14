package com.lixindi.gradproject.service;

import com.lixindi.gradproject.dto.CandidateResponse;
import com.lixindi.gradproject.dto.ServiceResponse;
import com.lixindi.gradproject.vo.CandidateInfo;
import com.lixindi.gradproject.vo.CandidateRequest;

import java.util.List;

/**
 * Created by lixindi on 2017/3/3.
 */
public interface CandidateService {
    ServiceResponse<Boolean> insertCandidate(List<CandidateInfo> candidateInfos);

    CandidateResponse getCandidate(CandidateRequest candidateRequest);

    List<String> getDepartment();
}
