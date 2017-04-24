package com.lixindi.gradproject.service;

import com.lixindi.gradproject.vo.CandidateInfo;
import com.lixindi.gradproject.vo.VoteResult;
import com.lixindi.gradproject.vo.VoteSetting;
import com.lixindi.gradproject.vo.VotedNum;

import java.util.List;

/**
 * Created by lixindi on 2017/3/27.
 */
public interface VoteService {
    void setVoteParam(VoteSetting voteSetting);

    void setNominees(List<CandidateInfo> candidates);

    void delKeys();

    List<CandidateInfo> getNominees();

    VoteSetting getVoteParam();

    VoteResult getVoteResult();

    Boolean validateId(int id);

    Boolean saveVoteResult(VoteResult voteResult);

    VotedNum getVotedNum();
}
