package com.lixindi.gradproject.redis;

import com.lixindi.gradproject.vo.VoteResult;
import com.lixindi.gradproject.vo.VoteSetting;

import java.util.List;

/**
 * Created by lixindi on 2017/3/27.
 */
public interface VoteDao {
    <T> void setKeyValue(String key ,T value);

    <T> T getValueByKey(String key);

    void delKeys(List<String> keys);

    boolean addIdToSet(int id);

    boolean isIdExists(int id);

    long getVotedNum();

    long getVoterNum();
}
