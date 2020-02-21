package com.wrx.schoolcould.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

/**
 * 生成Token的工具类
 */
public class TokenProccessor {

    /*
     *  生成 token
     */
    public static synchronized String makeToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(token.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    *   移除token
    * */
    public static void removeToken(HttpServletRequest request, String tokenServerkey) {
        request.getSession().removeAttribute(tokenServerkey);
    }

    /*
    *   比较token
    * */

    public static boolean equalsToken(HttpServletRequest request){

        String authorization = request.getHeader("Authorization");
        String token  = (String)request.getSession().getAttribute("Authorization");

        if(authorization == "" || authorization.isEmpty()){
            return false;
        }
        if (token == null || token.isEmpty()){
            return false;
        }
        if (authorization.equals(token)){
            return true;
        }
        return false;
    }
}