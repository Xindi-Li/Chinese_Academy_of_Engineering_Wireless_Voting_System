package com.lixindi.gradproject.redis;

import java.util.List;

/**
 * Created by lixindi on 2017/3/27.
 */
public interface VoteDao {
    <T> void setKeyValue(String key ,T value);

    <T> T getValueByKey(String key);

    void delKeys(List<String> keys);

    void delAllKeys();

    boolean addIdToSet(int id);

    boolean isIdExists(int id);

    long getVotedNum();

    long getVoterNum();
}
