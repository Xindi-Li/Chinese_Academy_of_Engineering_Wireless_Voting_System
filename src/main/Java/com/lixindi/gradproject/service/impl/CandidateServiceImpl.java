package com.lixindi.gradproject.service.impl;

import com.google.common.base.Strings;
import com.lixindi.gradproject.dao.CandidateMapper;
import com.lixindi.gradproject.dto.CandidateDaoRequest;
import com.lixindi.gradproject.dto.CandidateResponse;
import com.lixindi.gradproject.dto.ServiceResponse;
import com.lixindi.gradproject.service.CandidateService;
import com.lixindi.gradproject.utils.Status;
import com.lixindi.gradproject.vo.CandidateInfo;
import com.lixindi.gradproject.vo.CandidateRequest;
import com.lixindi.gradproject.vo.GroupRequest;
import com.lixindi.gradproject.vo.NomineeRequest;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public CandidateResponse getCandidate(CandidateRequest candidateRequest) {
        CandidateDaoRequest candidateDaoRequest = new CandidateDaoRequest();
        candidateDaoRequest.setDepartment(candidateRequest.getDepartment());
        candidateDaoRequest.setGroup(candidateRequest.getGroup());
        candidateDaoRequest.setName(candidateRequest.getName());
        if (candidateRequest.getItemsPerPage() != null && candidateRequest.getCurrentPage() != null) {
            int itemPerPage = candidateRequest.getItemsPerPage();
            int offset = (candidateRequest.getCurrentPage() - 1) * itemPerPage;
            candidateDaoRequest.setItemsPerPage(itemPerPage);
            candidateDaoRequest.setOffset(offset);
        }
        CandidateResponse candidateResponse = new CandidateResponse();
        List<CandidateInfo> candidateInfoList = candidateMapper.getCandidate(candidateDaoRequest);
        int total = candidateMapper.getTotal(candidateDaoRequest);
        candidateResponse.setCandidateInfos(candidateInfoList);
        candidateResponse.setTotal(total);
        return candidateResponse;
    }

    public List<CandidateInfo> getNominee(NomineeRequest request) {
        return candidateMapper.getNominee(request);
    }

    public List<String> getDepartment() {
        return candidateMapper.getDepartment();
    }

    public Boolean groupCandidate(GroupRequest groupRequest) {
        int lines = 0;
        List<String> names = groupRequest.getNames();
        String department = groupRequest.getDepartment();
        String group = groupRequest.getGroup();
        for (String name : names) {
            lines += candidateMapper.updateGroup(name, department, group);
        }
        return lines > 0;
    }

    public List<String> getGroupByDepartment(String department) {
        if (Strings.isNullOrEmpty(department)) {
            return null;
        } else {
            List<String> groups = candidateMapper.getGroupByDepartment(department);
            groups.remove(null);
            if (CollectionUtils.isEmpty(groups)) {
                return null;
            } else {
                return groups;
            }
        }
    }

    public Boolean updateCandidate(CandidateInfo candidateInfo) {
        int lines = candidateMapper.updateCandidate(candidateInfo);
        return lines > 0;
    }

    public Boolean deleteCandidate(CandidateInfo candidateInfo) {
        int lines = candidateMapper.deleteCandidate(candidateInfo);
        return lines > 0;
    }
}
