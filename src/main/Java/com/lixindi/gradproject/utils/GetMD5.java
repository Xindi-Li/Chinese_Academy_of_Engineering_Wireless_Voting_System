package com.lixindi.gradproject.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by lixindi on 2017/3/1.
 */
public class GetMD5 {
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值128位长整数的字节数组表示。
            // 最后使用BigInteger()和toString(16)把字节数组转为32位16进制字符串
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            return "MD5加密出现错误";
        }
    }
}
