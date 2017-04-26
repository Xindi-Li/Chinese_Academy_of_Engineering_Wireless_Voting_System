package com.lixindi.gradproject.controller;

import com.lixindi.gradproject.dto.AcademicianResponse;
import com.lixindi.gradproject.service.AcademicianService;
import com.lixindi.gradproject.utils.Status;
import com.lixindi.gradproject.vo.Academician;
import com.lixindi.gradproject.vo.AcademicianRequest;
import com.lixindi.gradproject.vo.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by JamesLee on 2017/4/24.
 */

@Controller
public class AcademicianController {
    @Autowired
    AcademicianService academicianService;

    @RequestMapping(value = "/admin/w_academician", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveAcademician(@RequestBody List<Academician> academicians) {
        academicianService.saveAcademician(academicians);
    }

    @RequestMapping(value = "/admin/r_academician", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<AcademicianResponse> getAcademician(@RequestBody AcademicianRequest request) {
        AcademicianResponse response = academicianService.getAcademician(request);
        return new AjaxResponse<>(Status.OK, response);
    }

    @RequestMapping(value = "/admin/r_depart", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<List<String>> getDepartment() {
        return new AjaxResponse<>(Status.OK, academicianService.getDepartement());
    }
}
