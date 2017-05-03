package com.lixindi.gradproject.service.impl;

import com.lixindi.gradproject.redis.VoteDao;
import com.lixindi.gradproject.service.VoteService;
import com.lixindi.gradproject.vo.*;
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

    public void setNominees(List<CandidateInfo> candidates) {
        voteDao.setKeyValue("nominees", candidates);
    }

    public void delKeys() {
        List<String> keys = new ArrayList<>();
        keys.add("ids");
        keys.add("voteParam");
        keys.add("voteResult");
        voteDao.delKeys(keys);
    }

    public List<CandidateInfo> getNominees() {
        return voteDao.getValueByKey("nominees");
    }

    public VoteSetting getVoteParam() {
        return voteDao.getValueByKey("voteParam");
    }

    public VoteResult getVoteResult() {
        return voteDao.getValueByKey("voteResult");
    }


    public RoundTimes getRoundTimes() {
        return voteDao.getValueByKey("roundtimes");
    }

    public Boolean validateId(int id) {
        return voteDao.isIdExists(id);
    }

    public Boolean saveVoteResult(VoteResult voteResult) {
        if (getVoteParam() != null) {
            voteDao.addIdToSet(voteResult.getVoterID());
            VoteResult lastResult = voteDao.getValueByKey("voteResult");
            if (lastResult != null) {
                for (int i = 0; i < voteResult.getCandidates().size(); i++) {
                    CandidateInfo last = lastResult.getCandidates().get(i);
                    CandidateInfo current = voteResult.getCandidates().get(i);
                    int saved = last.getScore();
                    int toAdd = current.getScore();
                    current.setScore(saved + toAdd);
                }
            }
            voteDao.setKeyValue("voteResult", voteResult);
            return true;
        } else {
            return false;
        }
    }


    public void saveRoundTimes(RoundTimes roundTimes) {
        voteDao.setKeyValue("roundtimes", roundTimes);
    }

    @Override
    public void saveVoteResultList(List<VoteResult> resultList) {
        voteDao.setKeyValue("voteResultList", resultList);
    }

    public VotedNum getVotedNum() {
        VotedNum votedNum = new VotedNum();
        votedNum.setTotal(voteDao.getVoterNum());
        votedNum.setVoted(voteDao.getVotedNum());
        return votedNum;
    }

    @Override
    public List<VoteResult> getVoteResultList() {
        return voteDao.getValueByKey("voteResultList");
    }
}
