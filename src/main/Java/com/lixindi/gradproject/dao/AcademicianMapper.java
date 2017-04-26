package com.lixindi.gradproject.dao;

import com.lixindi.gradproject.dto.AcademicianDaoRequest;
import com.lixindi.gradproject.vo.Academician;

import java.util.List;

public interface AcademicianMapper {

    int insert(List<Academician> academicians);

    List<Academician> getAcademicians(AcademicianDaoRequest record);

    List<String> getDepartment();

    int getTotal(AcademicianDaoRequest record);
}