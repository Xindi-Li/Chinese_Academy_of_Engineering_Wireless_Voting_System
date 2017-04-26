package com.lixindi.gradproject.service;

import com.lixindi.gradproject.dto.AcademicianResponse;
import com.lixindi.gradproject.vo.Academician;
import com.lixindi.gradproject.vo.AcademicianRequest;

import java.util.List;

/**
 * Created by JamesLee on 2017/4/24.
 */
public interface AcademicianService {
    void saveAcademician(List<Academician> academicians);

    List<String> getDepartement();

    AcademicianResponse getAcademician(AcademicianRequest request);
}
