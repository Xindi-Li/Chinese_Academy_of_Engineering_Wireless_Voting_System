package com.lixindi.gradproject.service.impl;

import com.lixindi.gradproject.dao.CandidateMapper;
import com.lixindi.gradproject.dto.ServiceResponse;
import com.lixindi.gradproject.service.CandidateService;
import com.lixindi.gradproject.utils.Status;
import com.lixindi.gradproject.vo.AjaxResponse;
import com.lixindi.gradproject.vo.CandidateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lixindi on 2017/3/3.
 */
@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateMapper candidateMapper;

    public ServiceResponse<Boolean> insertCandidate(List<CandidateInfo> candidateInfos) {
        try {
            int lines = candidateMapper.insertCandidate(candidateInfos);
            if (lines > 0) {
                return new ServiceResponse<Boolean>(Status.OK, true);
            } else {
                return new ServiceResponse<Boolean>(Status.ERROR, false);
            }
        } catch (DuplicateKeyException exception) {
            return new ServiceResponse<Boolean>(Status.DUPLICATE_KEY, false);
        }
    }
}
