package com.lixindi.gradproject.controller;

import com.lixindi.gradproject.service.VoteService;
import com.lixindi.gradproject.utils.GetMD5;
import com.lixindi.gradproject.utils.QRCodeGenerator;
import com.lixindi.gradproject.utils.Status;
import com.lixindi.gradproject.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by lixindi on 2017/3/22.
 */
@Controller
public class VoteController {
    @Autowired
    VoteService voteService;

    @RequestMapping(value = "/admin/w_vote_setting", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setVoteParam(@RequestBody VoteSetting voteSetting, @RequestParam int num) {
        voteService.setVoteParam(voteSetting);
        String address = null;
        try {
            address = "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/vote/vote.html";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        if (address != null) {
            QRCodeGenerator.GenerateQRCode(num, address);
        }
    }

    @RequestMapping(value = "/vote/r_vote_setting", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<VoteSetting> getVoteParam() {
        VoteSetting voteSetting = voteService.getVoteParam();
        if (voteSetting != null) {
            return new AjaxResponse<>(Status.OK, voteSetting);
        } else {
            return new AjaxResponse<>(Status.ERROR, null);
        }
    }

    @RequestMapping(value = "/vote/validate_querystring", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<Boolean> validateQueryString(@RequestParam int id, @RequestParam String token) {
        if (!GetMD5.getMD5("token" + id).equals(token)) {
            return new AjaxResponse<>(Status.WRONG_TOKEN, false);
        } else {
            if (voteService.validateId(id)) {
                return new AjaxResponse<>(Status.ID_EXSITS, false);
            } else {
                return new AjaxResponse<>(Status.OK, true);
            }
        }
    }

    @RequestMapping(value = "/vote/submit_vote", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> submitVote(HttpServletRequest request, @RequestBody VoteResult voteResult) {
        String id = request.getParameter("id");
        if (!GetMD5.getMD5("token" + id).equals(request.getParameter("token"))) {
            throw new RuntimeException("您没有操作此接口的权限");
        } else if (voteService.validateId(voteResult.getVoterID())) {
            return new AjaxResponse<>(Status.ID_EXSITS, false);
        } else if (voteService.saveVoteResult(voteResult)) {
            return new AjaxResponse<>(Status.OK, true);
        } else {
            return new AjaxResponse<>(Status.ERROR, false);
        }
    }

    @RequestMapping(value = "/vote/r_votednum", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<VotedNum> getVotedNum() {
        return new AjaxResponse<>(Status.OK, voteService.getVotedNum());
    }

    @RequestMapping(value = "/vote/r_vote_result", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<VoteResult> getVoteResult() {
        VoteResult voteResult = voteService.getVoteResult();
        if (voteResult == null) {
            voteService.delKeys();
            return new AjaxResponse<>(Status.ERROR, null);
        }
        List<CandidateInfo> candidates = voteResult.getCandidates();
        int advance_num = voteService.getVoteParam().getAdvance_num();
        voteService.delKeys();
        Collections.sort(candidates, (a, b) -> b.getScore() - a.getScore());
        ListIterator<CandidateInfo> listIterator = candidates.listIterator();
        int score = candidates.get(advance_num - 1).getScore();
        while (listIterator.hasNext()) {
            CandidateInfo candidate = listIterator.next();
            if (candidate.getScore() >= score) {
                candidate.setIs_advance(true);
            } else {
                candidate.setIs_advance(false);
            }
        }
        VoteResult result = new VoteResult();
        result.setAdvance_score(score);
        result.setCandidates(candidates);
        return new AjaxResponse<>(Status.OK, result);
    }

    @RequestMapping(value = "/admin/w_nominees", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setNominees(@RequestBody List<CandidateInfo> candidates) {
        voteService.setNominees(candidates);
    }

    @RequestMapping(value = "/vote/r_nominees", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<List<CandidateInfo>> getNominees() {
        return new AjaxResponse<>(Status.OK, voteService.getNominees());
    }
}
