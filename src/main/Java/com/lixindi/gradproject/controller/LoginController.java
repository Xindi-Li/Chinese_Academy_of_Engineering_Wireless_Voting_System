package com.lixindi.gradproject.controller;

import com.lixindi.gradproject.service.LoginService;
import com.lixindi.gradproject.utils.GetMD5;
import com.lixindi.gradproject.vo.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by lixindi on 2017/2/22.
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;


    @RequestMapping("/")
    public String loginpage() {
        return "redirect:/login/login.html";
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    @ResponseBody
    public boolean validate(HttpServletRequest Request, @RequestBody LoginInfo loginInfo) throws IOException {
        String passwordInDb = loginService.queryPassword(loginInfo.getUsername());
        String passwordInput = GetMD5.getMD5(loginInfo.getPassword());
        return passwordInDb.equals(passwordInput);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest Request) {
        Request.getSession().invalidate();
    }
}
