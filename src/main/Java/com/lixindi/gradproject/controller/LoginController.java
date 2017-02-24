package com.lixindi.gradproject.controller;

import com.lixindi.gradproject.vo.LoginInfo;
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
    @RequestMapping("/")
    public String loginpage() {
        return "redirect:/login/login.html";
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    @ResponseBody
    public boolean validate(HttpServletRequest Request, @RequestBody LoginInfo loginInfo) throws IOException {
        if ("123".equals(loginInfo.getUsername()) && "123".equals(loginInfo.getPassword())) {
            Request.getSession().setAttribute("user", loginInfo);
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public void logout(HttpServletRequest Request){
        Request.getSession().invalidate();
    }
}
