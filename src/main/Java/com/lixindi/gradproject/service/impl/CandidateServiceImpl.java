package com.lixindi.gradproject.service.impl;

import com.lixindi.gradproject.dao.CandidateMapper;
import com.lixindi.gradproject.service.CandidateService;
import com.lixindi.gradproject.vo.CandidateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lixindi on 2017/3/3.
 */
@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateMapper candidateMapper;

    public Boolean insertOne(CandidateInfo candidateInfo) {
        int lines = candidateMapper.insertCandidate(candidateInfo);
        return lines > 0;
    }
}
