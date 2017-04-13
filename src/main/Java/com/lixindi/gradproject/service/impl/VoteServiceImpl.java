package com.lixindi.gradproject.service.impl;

import com.lixindi.gradproject.redis.VoteDao;
import com.lixindi.gradproject.service.VoteService;
import com.lixindi.gradproject.vo.VoteResult;
import com.lixindi.gradproject.vo.VoteSetting;
import com.lixindi.gradproject.vo.VotedNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixindi on 2017/3/27.
 */
@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    VoteDao voteDao;

    public void setVoteParam(VoteSetting voteSetting) {
        voteDao.setKeyValue("voteParam", voteSetting);
    }

    public void delKeys() {
        List<String> keys = new ArrayList<String>();
        keys.add("ids");
        keys.add("voteParam");
        keys.add("voteResult");
        voteDao.delKeys(keys);
    }

    public VoteSetting getVoteParam() {
        return voteDao.getValueByKey("voteParam");
    }

    public VoteResult getVoteResult() {
        return voteDao.getValueByKey("voteResult");
    }

    public Boolean validateId(int id) {
        return voteDao.isIdExists(id);
    }

    public Boolean saveVoteResult(VoteResult voteResult) {
        if (getVoteParam() != null) {
            voteDao.addIdToSet(voteResult.getVoterID());
            if (voteDao.getValueByKey("voteResult") != null) {
                VoteResult lastResult = voteDao.getValueByKey("voteResult");
                for (int i = 0; i < voteResult.getCandidates().size(); i++) {
                    int saved = lastResult.getCandidates().get(i).getScore();
                    int toAdd = voteResult.getCandidates().get(i).getScore();
                    lastResult.getCandidates().get(i).setScore(saved + toAdd);
                }
                voteDao.setKeyValue("voteResult", lastResult);
            } else {
                voteDao.setKeyValue("voteResult", voteResult);
            }
            return true;
        } else {
            return false;
        }
    }

    public VotedNum getVotedNum() {
        VotedNum votedNum = new VotedNum();
        votedNum.setTotal(voteDao.getVoterNum());
        votedNum.setVoted(voteDao.getVotedNum());
        return votedNum;
    }
}
