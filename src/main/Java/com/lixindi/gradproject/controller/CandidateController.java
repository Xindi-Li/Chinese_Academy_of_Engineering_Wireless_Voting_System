package com.lixindi.gradproject.controller;

import com.lixindi.gradproject.vo.CandidateInfo;
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
    @RequestMapping(value = "/w_one_candidate", method = RequestMethod.POST)
    @ResponseBody
    public Boolean insertOne(@RequestBody List<Integer> number) {
        System.out.println(number.size());
        return true;
    }


}
