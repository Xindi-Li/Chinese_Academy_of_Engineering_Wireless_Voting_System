package com.lixindi.gradproject.controller;

import com.lixindi.gradproject.dto.ServiceResponse;
import com.lixindi.gradproject.service.CandidateService;
import com.lixindi.gradproject.utils.Status;
import com.lixindi.gradproject.vo.AjaxResponse;
import com.lixindi.gradproject.vo.CandidateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lixindi on 2017/2/27.
 */

@Controller
public class CandidateController {
    @Autowired
    CandidateService candidateService;

    @RequestMapping(value = "/w_candidate", method = RequestMethod.POST)
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
}
