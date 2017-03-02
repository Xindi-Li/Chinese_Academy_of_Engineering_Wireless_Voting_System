package com.lixindi.gradproject.dao;

import com.lixindi.gradproject.vo.AccountInfo;

public interface AdminMapper {
    String selectByUsername(String username);

    int insertAdmin(AccountInfo accountInfo);

    int updatePasswordByUsername(AccountInfo accountInfo);
}