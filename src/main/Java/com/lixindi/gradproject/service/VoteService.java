package com.lixindi.gradproject.service;

import com.lixindi.gradproject.vo.VoteResult;
import com.lixindi.gradproject.vo.VoteSetting;

/**
 * Created by lixindi on 2017/3/27.
 */
public interface VoteService {
    void setVoteParam(VoteSetting voteSetting);

    VoteSetting getVoteParam();

    Boolean getStatus();

    Boolean validateId(int id);

    Boolean saveVoteResult(VoteResult voteResult);
}
