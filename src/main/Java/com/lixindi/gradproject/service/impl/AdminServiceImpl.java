package com.lixindi.gradproject.service.impl;

import com.lixindi.gradproject.dao.AdminMapper;
import com.lixindi.gradproject.service.AdminService;
import com.lixindi.gradproject.utils.GetMD5;
import com.lixindi.gradproject.vo.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lixindi on 2017/3/1.
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;


    public Boolean validate(AccountInfo accountInfo) {
        String passwordInDb = adminMapper.selectByUsername(accountInfo.getUsername());
        String passwordInput = GetMD5.getMD5(accountInfo.getPassword());
        return passwordInput.equals(passwordInDb);
    }

    public Boolean register(AccountInfo accountInfo) {
        if (adminMapper.selectByUsername(accountInfo.getUsername()) != null) {
            return false;
        }
        AccountInfo encryptedAccount = new AccountInfo();
        encryptedAccount.setUsername(accountInfo.getUsername());
        String encryptedPassword = GetMD5.getMD5(accountInfo.getPassword());
        encryptedAccount.setPassword(encryptedPassword);

        int lines = adminMapper.insertAdmin(encryptedAccount);
        return lines != 0;
    }

    public Boolean resetPassword(AccountInfo accountInfo) {
        AccountInfo encryptedAccount = new AccountInfo();
        encryptedAccount.setUsername(accountInfo.getUsername());
        String encryptedPassword = GetMD5.getMD5(accountInfo.getPassword());
        encryptedAccount.setPassword(encryptedPassword);

        int lines = adminMapper.updatePasswordByUsername(encryptedAccount);
        return lines != 0;
    }
}
