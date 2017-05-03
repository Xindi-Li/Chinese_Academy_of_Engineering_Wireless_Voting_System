package com.lixindi.gradproject.redis.impl;

import com.lixindi.gradproject.redis.VoteDao;
import com.lixindi.gradproject.vo.VoteSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lixindi on 2017/3/27.
 */
@Repository
public class VoteDaoImpl implements VoteDao {
    @Autowired
    private RedisTemplate redisTemplate;

    public <T> void setKeyValue(String key, T value) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public <T> T getValueByKey(String key) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void delKeys(List<String> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public void delAllKeys() {
        redisTemplate.delete(redisTemplate.keys("*"));
    }

    public boolean addIdToSet(int id) {
        SetOperations<String, Integer> setOperations = redisTemplate.opsForSet();
        return setOperations.add("ids", id);
    }

    public boolean isIdExists(int id) {
        SetOperations<String, Integer> setOperations = redisTemplate.opsForSet();
        return setOperations.isMember("ids", id);
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
