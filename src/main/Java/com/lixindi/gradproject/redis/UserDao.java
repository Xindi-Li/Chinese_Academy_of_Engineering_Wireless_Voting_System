package com.lixindi.gradproject.redis;

import com.lixindi.gradproject.vo.AccountInfo;

/**
 * Created by lixindi on 2017/3/23.
 */
public interface UserDao {

    void add();

    AccountInfo get(String key);
}
