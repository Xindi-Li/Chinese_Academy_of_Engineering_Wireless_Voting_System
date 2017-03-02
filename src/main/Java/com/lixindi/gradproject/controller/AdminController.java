package com.lixindi.gradproject.controller;

import com.lixindi.gradproject.service.AdminService;
import com.lixindi.gradproject.vo.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by lixindi on 2017/2/22.
 */
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;


    @RequestMapping("/")
    public String loginpage() {
        return "redirect:/login/login.html";
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    @ResponseBody
    public Boolean validate(HttpSession httpSession, @RequestBody AccountInfo accountInfo) {
        if (adminService.validate(accountInfo)) {
            httpSession.setAttribute("user", accountInfo);
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Boolean register(@RequestBody AccountInfo accountInfo) {
        return adminService.register(accountInfo);
    }

    @RequestMapping(value = "/get_username", method = RequestMethod.GET)
    @ResponseBody
    public String getUsername(HttpSession httpSession) {
        return ((AccountInfo) httpSession.getAttribute("user")).getUsername();
    }

    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    @ResponseBody
    public Boolean resetPassword(@RequestBody AccountInfo accountInfo) {
        return adminService.resetPassword(accountInfo);
    }
}
