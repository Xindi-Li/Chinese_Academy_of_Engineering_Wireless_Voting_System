package com.lixindi.gradproject.controller;

import com.lixindi.gradproject.service.VoteService;
import com.lixindi.gradproject.utils.GetMD5;
import com.lixindi.gradproject.utils.QRCodeGenerator;
import com.lixindi.gradproject.utils.Status;
import com.lixindi.gradproject.vo.AjaxResponse;
import com.lixindi.gradproject.vo.VoteResult;
import com.lixindi.gradproject.vo.VoteSetting;
import com.lixindi.gradproject.vo.VotedNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

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

    @RequestMapping(value = "/admin/generate_qrcode", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void generateQRCode(@RequestParam int num) {
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

    @RequestMapping(value = "/vote/validate_querystring", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<Boolean> validateQueryString(@RequestParam int id, @RequestParam String token) {
        if (!GetMD5.getMD5("123").equals(token)) {
            return new AjaxResponse<Boolean>(Status.WRONG_TOKEN, false);
        } else {
            if (voteService.validateId(id)) {
                return new AjaxResponse<Boolean>(Status.ID_EXSITS, false);
            } else {
                return new AjaxResponse<Boolean>(Status.OK, true);
            }
        }
    }

    @RequestMapping(value = "/vote/submit_vote", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> submitVote(HttpServletRequest request, @RequestBody VoteResult voteResult) {
        if (!GetMD5.getMD5("123").equals(request.getParameter("token"))) {
            throw new RuntimeException("您没有操作此接口的权限");
        } else {
            if (voteService.saveVoteResult(voteResult)) {
                return new AjaxResponse<Boolean>(Status.OK, true);
            } else {
                return new AjaxResponse<Boolean>(Status.ERROR, false);
            }
        }
    }

    @RequestMapping(value = "/vote/r_votednum", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<VotedNum> getVotedNum() {
        return new AjaxResponse<VotedNum>(Status.OK, voteService.getVotedNum());
    }

    @RequestMapping(value = "/vote/endvote",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void endVote(){

    }
}
