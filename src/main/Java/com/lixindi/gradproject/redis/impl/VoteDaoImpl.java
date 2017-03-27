package com.lixindi.gradproject.redis.impl;

import com.lixindi.gradproject.vo.VoteSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;
import com.lixindi.gradproject.redis.VoteDao;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by lixindi on 2017/3/27.
 */
@Repository
public class VoteDaoImpl implements VoteDao {
    @Autowired
    private RedisTemplate redisTemplate;

    public void setVoteParam(VoteSetting voteSetting) {
        ListOperations<String, VoteSetting> listOperations = redisTemplate.opsForList();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

        listOperations.leftPush("voteParam", voteSetting);
    }

    public void updateStatus(boolean status) {
        ValueOperations<String, Boolean> valueOperations = redisTemplate.opsForValue();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

        valueOperations.set("is_start", status);
    }

    public boolean getStatus() {
        ValueOperations<String, Boolean> valueOperations = redisTemplate.opsForValue();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

        if (redisTemplate.hasKey("is_start")) {
            return valueOperations.get("is_start");
        } else {
            return false;
        }
    }

    public VoteSetting getVoteParam() {
        ListOperations<String, VoteSetting> listOperations = redisTemplate.opsForList();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

        return listOperations.index("voteParam", 0);
    }


}
