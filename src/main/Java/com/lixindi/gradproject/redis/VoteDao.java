package com.lixindi.gradproject.redis;

import com.lixindi.gradproject.vo.VoteSetting;

/**
 * Created by lixindi on 2017/3/27.
 */
public interface VoteDao {
    void setVoteParam(VoteSetting voteSetting);

    void updateStatus(boolean status);

    boolean getStatus();

    VoteSetting getVoteParam();

    boolean isKeyExists(String key);

    boolean addIdToSet(int id);
}
