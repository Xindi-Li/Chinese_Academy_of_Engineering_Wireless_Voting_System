package com.lixindi.gradproject.service.impl;

import com.lixindi.gradproject.redis.VoteDao;
import com.lixindi.gradproject.service.VoteService;
import com.lixindi.gradproject.vo.VoteSetting;
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
}
