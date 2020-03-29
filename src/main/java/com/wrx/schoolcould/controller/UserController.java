package com.wrx.schoolcould.controller;

import com.wrx.schoolcould.dto.SigninDTO;
import com.wrx.schoolcould.pojo.User;
import com.wrx.schoolcould.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户登录行为
     *
     * @param req
     * @param resp
     * @return
     */
    @PostMapping("/signin")
    public Object signin(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("=======================================================================================");
        System.out.println("Authorization :" + req.getSession().getAttribute("Authorization"));

        SigninDTO signinDTO = userService.signinProcess(req, resp);
        System.out.println(signinDTO);

        System.out.println("Authorization :" + req.getSession().getAttribute("Authorization"));

        return signinDTO;

    }

    /**
     * 获取当前页进行分页操作
     *
     * @param currentPage
     * @return
     */
    @PostMapping("/list/{currentPage}")
    public Object findAll(@PathVariable(value = "currentPage", required = false) Integer currentPage) {

        return userService.list(currentPage);
    }

    /**
     * 根据id获取对象 根据state修改user状态
     *
     * @param userID
     * @param state
     * @return
     */
    @PostMapping("/updateUserState/{userID}/{state}")
    public Object updateUserState(@PathVariable(value = "userID", required = true) Integer userID,
                                  @PathVariable(value = "state", required = true) Boolean state) {

        return userService.updateUserState(userID, state);
    }

    /**
     * 根据id获取user
     *
     * @param id
     * @return
     */
    @PostMapping("/getUser/{id}")
    public Object getUser(@PathVariable(value = "id", required = true) Integer id) {

        return userService.findUserById(id);
    }

    /**
     * 根据回传数据更新用户
     *
     * @return
     */
    @PostMapping("/updateUser")
    public Object updateUser(@RequestBody User user) throws IllegalAccessException {

        return userService.updateUser(user);
    }


}
