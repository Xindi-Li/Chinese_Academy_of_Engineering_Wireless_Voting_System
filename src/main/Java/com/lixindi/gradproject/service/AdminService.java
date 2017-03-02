package com.lixindi.gradproject.service;

import com.lixindi.gradproject.vo.AccountInfo;

/**
 * Created by lixindi on 2017/3/1.
 */
public interface AdminService {
    Boolean validate(AccountInfo accountInfo);
    Boolean register(AccountInfo accountInfo);
    Boolean resetPassword(AccountInfo accountInfo);
}
