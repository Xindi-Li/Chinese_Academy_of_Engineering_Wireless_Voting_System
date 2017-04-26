package com.lixindi.gradproject.service.impl;

import com.lixindi.gradproject.dao.AcademicianMapper;
import com.lixindi.gradproject.dto.AcademicianDaoRequest;
import com.lixindi.gradproject.dto.AcademicianResponse;
import com.lixindi.gradproject.service.AcademicianService;
import com.lixindi.gradproject.vo.Academician;
import com.lixindi.gradproject.vo.AcademicianRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JamesLee on 2017/4/24.
 */
@Service
public class AcademicianServiceImpl implements AcademicianService {
    @Autowired
    AcademicianMapper academicianMapper;

    @Override
    public void saveAcademician(List<Academician> academicians) {
        academicianMapper.insert(academicians);
    }

    @Override
    public List<String> getDepartement() {
        return academicianMapper.getDepartment();
    }

    @Override
    public AcademicianResponse getAcademician(AcademicianRequest request) {
        AcademicianDaoRequest daoRequest = new AcademicianDaoRequest();
        daoRequest.setDepartment(request.getDepartment());
        daoRequest.setName(request.getName());
        int itemPerPage = request.getItemsPerPage();
        int offset = itemPerPage * (request.getCurrentPage() - 1);
        daoRequest.setItemsPerPage(itemPerPage);
        daoRequest.setOffset(offset);

        List<Academician> academicians = academicianMapper.getAcademicians(daoRequest);
        int total = academicianMapper.getTotal(daoRequest);

        AcademicianResponse response = new AcademicianResponse();
        response.setAcademician(academicians);
        response.setTotal(total);

        return response;
    }
}
