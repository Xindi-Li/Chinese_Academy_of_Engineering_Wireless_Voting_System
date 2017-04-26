package com.lixindi.gradproject.controller;

import com.lixindi.gradproject.service.AdminService;
import com.lixindi.gradproject.utils.Status;
import com.lixindi.gradproject.vo.AccountInfo;
import com.lixindi.gradproject.vo.AjaxResponse;
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
    public AjaxResponse<Boolean> validate(HttpSession httpSession, @RequestBody AccountInfo accountInfo) {
        if (adminService.validate(accountInfo)) {
            httpSession.setAttribute("user", accountInfo);
            return new AjaxResponse<>(Status.OK, true);
        } else {
            return new AjaxResponse<>(Status.ERROR, false);
        }
    }

    @RequestMapping(value = "/admin/logout", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }

    @RequestMapping(value = "/admin/register", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> register(@RequestBody AccountInfo accountInfo) {
        Boolean is_Success = adminService.register(accountInfo);
        if (is_Success) {
            return new AjaxResponse<>(Status.OK, true);
        } else {
            return new AjaxResponse<>(Status.ERROR, false);
        }
    }

    @RequestMapping(value = "/admin/get_username", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<String> getUsername(HttpSession httpSession) {
        String username = ((AccountInfo) httpSession.getAttribute("user")).getUsername();
        return new AjaxResponse<>(Status.OK, username);
    }

    @RequestMapping(value = "/admin/reset_password", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> resetPassword(@RequestBody AccountInfo accountInfo) {
        if (adminService.resetPassword(accountInfo)) {
            return new AjaxResponse<>(Status.OK, true);
        } else {
            return new AjaxResponse<>(Status.ERROR, false);
        }
    }
}
