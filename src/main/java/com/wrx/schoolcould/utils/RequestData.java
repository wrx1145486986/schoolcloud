package com.wrx.schoolcould.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/*
 *    author: 王荣祥
 *    date  ：2020.2.18
 *
 * */
public class RequestData {

    //将请求体内的 json 数据转化为 JSONObject 对象
    public static JSONObject getRequestBodyToJSONObject(HttpServletRequest req) {

        String requestBody = "";

        try (BufferedReader reader = req.getReader()) {

            String warp = "";
            while ((warp = reader.readLine()) != null) {
                requestBody += warp;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSON.parseObject(requestBody);

    }
}
