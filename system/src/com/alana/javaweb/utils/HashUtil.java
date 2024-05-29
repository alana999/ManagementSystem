package com.alana.javaweb.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    /**
     * 这个方法用于将明文密码转换成哈希值。
     * 它使用一个安全的哈希算法（SHA-256）来生成密码的哈希。这是为了确保在数据库中不存储明文密码，增加安全性。
     * @param password 用户输入的密码
     * @return
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 验证用户输入的密码与存储在数据库中的哈希密码是否匹配。
     * 它首先会将输入的密码通过相同的哈希算法处理，然后将结果与数据库中存储的哈希进行比对
     * @param password
     * @param hashedPassword
     * @return
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        String hashedInput = hashPassword(password);
        return hashedInput.equals(hashedPassword);
    }
}
