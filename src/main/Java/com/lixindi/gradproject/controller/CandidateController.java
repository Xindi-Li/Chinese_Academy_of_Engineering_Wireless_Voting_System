package com.lixindi.gradproject.controller;

import com.lixindi.gradproject.dto.CandidateResponse;
import com.lixindi.gradproject.dto.ServiceResponse;
import com.lixindi.gradproject.service.CandidateService;
import com.lixindi.gradproject.utils.Status;
import com.lixindi.gradproject.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            return new AjaxResponse<>(Status.OK, true);
        } else if (insert.getStatus() == Status.DUPLICATE_KEY) {
            return new AjaxResponse<>(Status.DUPLICATE_KEY, false);
        } else {
            return new AjaxResponse<>(Status.ERROR, false);
        }
    }

    @RequestMapping(value = "/admin/r_candidate", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<CandidateResponse> getCandidate(@RequestBody CandidateRequest candidateRequest) {
        CandidateResponse candidateResponse = candidateService.getCandidate(candidateRequest);
        return new AjaxResponse<>(Status.OK, candidateResponse);
    }

    @RequestMapping(value = "/admin/u_candidate", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> updateCandidate(@RequestBody CandidateInfo candidateInfo) {
        if (candidateService.updateCandidate(candidateInfo)) {
            return new AjaxResponse<>(Status.OK, true);
        } else {
            return new AjaxResponse<>(Status.ERROR, false);
        }
    }

    @RequestMapping(value = "/admin/d_candidate", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delteCandidate(@RequestBody CandidateInfo candidateInfo) {
        candidateService.deleteCandidate(candidateInfo);
    }

    @RequestMapping(value = "/admin/r_department", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<List<String>> getDepartment() {
        List<String> departments = candidateService.getDepartment();
        return new AjaxResponse<>(Status.OK, departments);
    }

    @RequestMapping(value = "/admin/w_group", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> groupCandidate(@RequestBody GroupRequest groupRequest) {
        Boolean updated = candidateService.groupCandidate(groupRequest);
        if (updated) {
            return new AjaxResponse<>(Status.OK, true);
        } else {
            return new AjaxResponse<>(Status.ERROR, false);
        }
    }

    @RequestMapping(value = "/admin/r_group", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<List<String>> getGroupByDepartment(@RequestBody GroupRequest groupRequest) {
        List<String> groups = candidateService.getGroupByDepartment(groupRequest.getDepartment());
        return new AjaxResponse<>(Status.OK, groups);
    }

    @RequestMapping(value = "/admin/r_conditions", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<List<String>> getConditions() {
        List<String> conditions = new ArrayList<String>();
        List<String> departments = candidateService.getDepartment();
        for (String department : departments) {
            List<String> groups = candidateService.getGroupByDepartment(department);
            if (groups == null) {
                conditions.add(department + "学部");
            } else {
                for (String group : groups) {
                    conditions.add(department + "学部" + group + "组");
                }
            }
        }
        return new AjaxResponse<>(Status.OK, conditions);
    }

    @RequestMapping(value = "/admin/r_nominees_db", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<List<CandidateInfo>> getNominees(@RequestBody List<NomineeRequest> nomineeRequest) {
        List<CandidateInfo> candidates = new ArrayList<>();
        nomineeRequest.forEach(e -> candidates.addAll(candidateService.getNominee(e)));
        return new AjaxResponse<>(Status.OK, candidates);
    }

}
