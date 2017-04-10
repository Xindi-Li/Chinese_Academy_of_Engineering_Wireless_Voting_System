package com.lixindi.gradproject.redis.impl;

import com.lixindi.gradproject.redis.VoteDao;
import com.lixindi.gradproject.vo.VoteResult;
import com.lixindi.gradproject.vo.VoteSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by lixindi on 2017/3/27.
 */
@Repository
public class VoteDaoImpl implements VoteDao {
    @Autowired
    private RedisTemplate redisTemplate;

    public void setVoteParam(VoteSetting voteSetting) {
        ValueOperations<String, VoteSetting> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("voteParam", voteSetting);
    }

    public void updateStatus(boolean status) {
        ValueOperations<String, Boolean> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("is_start", status);
    }

    public boolean getStatus() {
        ValueOperations<String, Boolean> valueOperations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey("is_start")) {
            return valueOperations.get("is_start");
        } else {
            return false;
        }
    }

    public VoteSetting getVoteParam() {
        ValueOperations<String, VoteSetting> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get("voteParam");
    }

    public VoteResult getVoteResult() {
        ValueOperations<String, VoteResult> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get("voteResult");
    }

    public boolean isKeyExists(String key) {
        return redisTemplate.hasKey(key);
    }

    public boolean addIdToSet(int id) {
        SetOperations<String, Integer> setOperations = redisTemplate.opsForSet();
        return setOperations.add("ids", id);
    }

    public boolean isIdExists(int id) {
        SetOperations<String, Integer> setOperations = redisTemplate.opsForSet();
        return setOperations.isMember("ids", id);
    }

    public void setVoteResult(VoteResult voteResult) {
        ValueOperations<String, VoteResult> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("voteResult", voteResult);
    }

    public long getVotedNum() {
        SetOperations<String, Integer> setOperations = redisTemplate.opsForSet();
        return setOperations.size("ids");
    }

    public long getVoterNum() {
        ValueOperations<String, VoteSetting> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get("voteParam").getVoter_num();
    }
}
