package com.wrx.schoolcould.service;

import com.alibaba.fastjson.JSONObject;
import com.wrx.schoolcould.dto.SigninDTO;
import com.wrx.schoolcould.mapper.UserMapper;
import com.wrx.schoolcould.pojo.User;
import com.wrx.schoolcould.utils.RequestData;
import com.wrx.schoolcould.utils.TokenProccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SignService {

    @Autowired
    UserMapper userMapper;

    public SigninDTO signinProcess(HttpServletRequest req, HttpServletResponse resp) {

        SigninDTO signinDTO = new SigninDTO();

        JSONObject requestBody = RequestData.getRequestBodyToJSONObject(req);

        if (requestBody != null){
            String email = (String) requestBody.get("email");
            String password = (String) requestBody.get("password");
//        boolean remember = (boolean) requestBody.get("remember");

//        验证session中的token 与 请求头中的 token 是否相等
            if (!TokenProccessor.equalsToken(req)) {
//            如果两者不相等则查库比较 邮箱和地址
                User user = userMapper.findByEmail(email);

                if (user != null) {
                    if (user.getUpwd().equals(password)) {
//                    账号密码正确
                        signinDTO.setCode(200);
                        signinDTO.setId(user.getId());
                        signinDTO.setEmail(user.getEmail());
                        signinDTO.setMsg("OK");
//                    回写token
                        String token = TokenProccessor.makeToken();
                        req.getSession().setAttribute("Authorization", token);
                        signinDTO.setToken(token);

                    } else {
                        signinDTO.setCode(402);
                        signinDTO.setMsg("密码不正确");
                    }
                } else {
                    signinDTO.setCode(401);
                    signinDTO.setMsg("账号不存在");
                }

            } else {
//            如果相等则返回数据
                signinDTO.setCode(200);
                signinDTO.setMsg("OK");
            }
        }else {
            signinDTO.setCode(403);
            signinDTO.setMsg("未查到请求负载数据");
        }

        return signinDTO;
    }

}
