package com.lixindi.gradproject.service.impl;

import com.lixindi.gradproject.redis.VoteDao;
import com.lixindi.gradproject.service.VoteService;
import com.lixindi.gradproject.vo.VoteResult;
import com.lixindi.gradproject.vo.VoteSetting;
import com.lixindi.gradproject.vo.VotedNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lixindi on 2017/3/27.
 */
@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    VoteDao voteDao;

    public void setVoteParam(VoteSetting voteSetting) {
        voteDao.setVoteParam(voteSetting);
        voteDao.updateStatus(true);
    }

    public VoteSetting getVoteParam() {
        return voteDao.getVoteParam();
    }

    public Boolean getStatus() {
        return voteDao.getStatus();
    }

    public Boolean validateId(int id) {
        return voteDao.isIdExists(id);
    }

    public Boolean saveVoteResult(VoteResult voteResult) {
        if (voteDao.getStatus()) {
            voteDao.addIdToSet(voteResult.getVoterID());
            if (voteDao.isKeyExists("voteResult")) {
                VoteResult lastResult = voteDao.getVoteResult();
                for (int i = 0; i < voteResult.getCandidates().size(); i++) {
                    int saved = lastResult.getCandidates().get(i).getScore();
                    int toAdd = voteResult.getCandidates().get(i).getScore();
                    lastResult.getCandidates().get(i).setScore(saved + toAdd);
                }
                voteDao.setVoteResult(lastResult);
            } else {
                voteDao.setVoteResult(voteResult);
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
