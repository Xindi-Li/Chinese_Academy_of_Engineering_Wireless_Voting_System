package com.lixindi.gradproject.controller;

import com.lixindi.gradproject.service.VoteService;
import com.lixindi.gradproject.utils.Status;
import com.lixindi.gradproject.vo.AjaxResponse;
import com.lixindi.gradproject.vo.VoteSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lixindi on 2017/3/22.
 */
@Controller
public class VoteController {
    @Autowired
    VoteService voteService;

    @RequestMapping(value = "/admin/w_vote_setting", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setVoteParam(@RequestBody VoteSetting voteSetting) {
        voteService.setVoteParam(voteSetting);
    }

    @RequestMapping(value = "/vote/r_vote_setting", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<VoteSetting> getVoteParam() {
        if (!voteService.getStatus()) {
            return new AjaxResponse<VoteSetting>(Status.ERROR, null);
        }
        VoteSetting voteSetting = voteService.getVoteParam();
        if (voteSetting != null) {
            return new AjaxResponse<VoteSetting>(Status.OK, voteSetting);
        } else {
            return new AjaxResponse<VoteSetting>(Status.ERROR, null);
        }
    }
}
