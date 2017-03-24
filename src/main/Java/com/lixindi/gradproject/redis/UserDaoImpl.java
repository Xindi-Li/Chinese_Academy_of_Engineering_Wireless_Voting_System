package com.lixindi.gradproject.redis;

import com.lixindi.gradproject.redis.UserDao;
import com.lixindi.gradproject.vo.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

/**
 * Created by lixindi on 2017/3/23.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private RedisTemplate redisTemplate;


    public void add() {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setUsername("123");
        accountInfo.setUsername("123");

        ValueOperations<String, AccountInfo> operations = redisTemplate.opsForValue();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        operations.set("abc", accountInfo);
    }

    public AccountInfo get(String key) {
        ValueOperations<String, AccountInfo> operations = redisTemplate.opsForValue();
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        return operations.get("abc");
    }
}
