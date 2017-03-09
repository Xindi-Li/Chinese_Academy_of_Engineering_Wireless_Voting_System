package com.lixindi.gradproject.controller;

import com.lixindi.gradproject.service.CandidateService;
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
    public Boolean insertCandidate(@RequestBody List<CandidateInfo> candidateInfos) {
        return candidateService.insertCandidate(candidateInfos);
    }
}
