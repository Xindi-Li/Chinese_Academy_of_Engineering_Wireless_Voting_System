package com.lixindi.gradproject.service;

import com.lixindi.gradproject.vo.*;

import java.util.List;

/**
 * Created by lixindi on 2017/3/27.
 */
public interface VoteService {
    List<CandidateInfo> getNominees();

    VoteSetting getVoteParam();

    VoteResult getVoteResult();

    RoundTimes getRoundTimes();

    VotedNum getVotedNum();

    List<VoteResult> getVoteResultList();

    Boolean validateId(int id);

    void setVoteParam(VoteSetting voteSetting);

    void setNominees(List<CandidateInfo> candidates);

    void delKeys();

    Boolean saveVoteResult(VoteResult voteResult);

    void saveRoundTimes(RoundTimes roundTimes);

    void saveVoteResultList(List<VoteResult> resultList);
}
