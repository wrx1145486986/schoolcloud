package com.wrx.schoolcould.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wrx.schoolcould.dto.SigninDTO;
import com.wrx.schoolcould.dto.UserBaseDate;
import com.wrx.schoolcould.dto.UserDTO;
import com.wrx.schoolcould.mapper.UserMapper;
import com.wrx.schoolcould.pojo.User;
import com.wrx.schoolcould.utils.Convert;
import com.wrx.schoolcould.utils.RequestData;
import com.wrx.schoolcould.utils.TokenProccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    /*
     *   用户登录处理方法
     * */
    public SigninDTO signinProcess(HttpServletRequest req, HttpServletResponse resp) {

        SigninDTO signinDTO = new SigninDTO();

        JSONObject requestBody = RequestData.getRequestBodyToJSONObject(req);

        if (requestBody != null) {
            String email = (String) requestBody.get("email");
            String password = (String) requestBody.get("password");
//        boolean remember = (boolean) requestBody.get("remember");

//        验证session中的token 与 请求头中的 token 是否相等
            if (!TokenProccessor.equalsToken(req)) {
//            如果两者不相等则查库比较 邮箱和地址
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("email", email)
                        .eq("password", password);
                User user = userMapper.selectOne(queryWrapper);

                if (user != null) {
                    if (user.getPassword().equals(password)) {
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
        } else {
            signinDTO.setCode(403);
            signinDTO.setMsg("未查到请求负载数据");
        }

        return signinDTO;
    }

    /*
     *查询全部用户
     *  */
    public UserDTO list(Integer currentPage) {
        Integer userTotal = this.userMapper.selectCount();

        if (currentPage == null || currentPage < 1 || currentPage > (Math.ceil((userTotal % 8)) + (userTotal / 8))) {
            currentPage = 1;
        }

        //        分完页的用户数据
        List<User> allDate = userMapper.list(((currentPage - 1) * 8), (currentPage * 8));

        UserDTO userDTO = new UserDTO();
        List<UserBaseDate> baseDates = new ArrayList<>();

        for (User date : allDate) {
            UserBaseDate userBaseDate = new UserBaseDate();
            userBaseDate.setId(date.getId());
            userBaseDate.setEmail(date.getEmail());
            userBaseDate.setLastSigninSite(date.getLastSigninSite());
            userBaseDate.setState(date.getState());
            baseDates.add(userBaseDate);
        }
        userDTO.setCode(200);
        userDTO.setMsg("OK");
        userDTO.setBaseDates(baseDates);
        userDTO.setDateTotal(userTotal);

        return userDTO;

    }

    //    查询用户总数
    public Integer UserTotal() {
        return userMapper.selectCount();
    }

    //    修改用户状态
    public UserDTO updateUserState(Integer userID, Boolean state) {
        Integer flag;

        UserDTO userDTO = new UserDTO();

        if (state) {
            flag = 1;
        } else {
            flag = 0;
        }
        if (userMapper.updateUserStateById(userID, flag) == 1) {
            userDTO.setUser(userMapper.selectUserStateById(userID));
            userDTO.setCode(200);
            userDTO.setMsg("OK");
        } else {
            userDTO.setCode(400);
            userDTO.setMsg("FAIL");
        }
        return userDTO;
    }

    /*
     * 根据id查找用户
     * */
    public UserDTO findUserById(Integer id) {
        User user = userMapper.selectById(id);
        UserDTO userDTO = new UserDTO();
        if (user != null){
            userDTO.setUser(user);
            userDTO.setCode(200);
            userDTO.setMsg("OK");
            userDTO.setDateTotal(1);
        }else {
            userDTO.setCode(404);
            userDTO.setMsg("FAIL");
            userDTO.setDateTotal(0);
        }
        return userDTO;
    }

    public UserDTO updateUser(User user) throws IllegalAccessException {

//        将user中的空字段转换为 null
        Convert.emptyToNull(user);

        UserDTO userDTO = new UserDTO();
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", user.getId());
        if (userMapper.update(user, updateWrapper) == 1){
            userDTO.setCode(200);
            userDTO.setMsg("OK");
        }else {
            userDTO.setCode(400);
            userDTO.setMsg("FAIL");
        }

        return userDTO;
    }
}
