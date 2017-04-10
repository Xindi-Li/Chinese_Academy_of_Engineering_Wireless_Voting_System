package com.lixindi.gradproject.redis;

import com.lixindi.gradproject.vo.VoteResult;
import com.lixindi.gradproject.vo.VoteSetting;

/**
 * Created by lixindi on 2017/3/27.
 */
public interface VoteDao {
    void setVoteParam(VoteSetting voteSetting);

    void updateStatus(boolean status);

    boolean getStatus();

    VoteSetting getVoteParam();

    VoteResult getVoteResult();

    boolean isKeyExists(String key);

    boolean addIdToSet(int id);

    boolean isIdExists(int id);

    void setVoteResult(VoteResult voteResult);

    long getVotedNum();

    long getVoterNum();
}
