package com.lixindi.gradproject.controller;

import com.lixindi.gradproject.dto.CandidateResponse;
import com.lixindi.gradproject.dto.ServiceResponse;
import com.lixindi.gradproject.service.CandidateService;
import com.lixindi.gradproject.utils.Status;
import com.lixindi.gradproject.vo.AjaxResponse;
import com.lixindi.gradproject.vo.CandidateInfo;
import com.lixindi.gradproject.vo.CandidateRequest;
import com.lixindi.gradproject.vo.GroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lixindi on 2017/2/27.
 */

@Controller
public class CandidateController {
    @Autowired
    CandidateService candidateService;

    @RequestMapping(value = "/admin/w_candidate", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> insertCandidate(@RequestBody List<CandidateInfo> candidateInfos) {
        ServiceResponse insert = candidateService.insertCandidate(candidateInfos);
        if ((Boolean) insert.getData()) {
            return new AjaxResponse<Boolean>(Status.OK, true);
        } else if (insert.getStatus() == Status.DUPLICATE_KEY) {
            return new AjaxResponse<Boolean>(Status.DUPLICATE_KEY, false);
        } else {
            return new AjaxResponse<Boolean>(Status.ERROR, false);
        }
    }

    @RequestMapping(value = "/admin/r_candidate", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<CandidateResponse> getCandidate(@RequestBody CandidateRequest candidateRequest) {
        CandidateResponse candidateResponse = candidateService.getCandidate(candidateRequest);
        return new AjaxResponse<CandidateResponse>(Status.OK, candidateResponse);
    }

    @RequestMapping(value = "/admin/r_department", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<List<String>> getDepartment() {
        List<String> departments = candidateService.getDepartment();
        return new AjaxResponse<List<String>>(Status.OK, departments);
    }

    @RequestMapping(value = "/admin/w_group", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> groupCandidate(@RequestBody GroupRequest groupRequest) {
        Boolean updated = candidateService.groupCandidate(groupRequest);
        if (updated) {
            return new AjaxResponse<Boolean>(Status.OK, true);
        } else {
            return new AjaxResponse<Boolean>(Status.ERROR, false);
        }
    }

    @RequestMapping(value = "/admin/r_group", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<List<String>> getGroupByDepartment(@RequestBody GroupRequest groupRequest) {
        List<String> groups = candidateService.getGroupByDepartment(groupRequest.getDepartment());
        return new AjaxResponse<List<String>>(Status.OK, groups);
    }
}
