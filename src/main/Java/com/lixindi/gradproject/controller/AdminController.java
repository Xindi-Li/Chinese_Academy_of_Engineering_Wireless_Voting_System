package com.lixindi.gradproject.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lixindi.gradproject.service.AdminService;
import com.lixindi.gradproject.utils.Status;
import com.lixindi.gradproject.vo.AccountInfo;
import com.lixindi.gradproject.vo.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

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
            return new AjaxResponse<Boolean>(Status.OK, true);
        } else {
            return new AjaxResponse<Boolean>(Status.ERROR, false);
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
            return new AjaxResponse<Boolean>(Status.OK, true);
        } else {
            return new AjaxResponse<Boolean>(Status.ERROR, false);
        }
    }

    @RequestMapping(value = "/admin/get_username", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse<String> getUsername(HttpSession httpSession) {
        String username = ((AccountInfo) httpSession.getAttribute("user")).getUsername();
        return new AjaxResponse<String>(Status.OK, username);
    }

    @RequestMapping(value = "/admin/reset_password", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse<Boolean> resetPassword(@RequestBody AccountInfo accountInfo) {
        if (adminService.resetPassword(accountInfo)) {
            return new AjaxResponse<Boolean>(Status.OK, true);
        } else {
            return new AjaxResponse<Boolean>(Status.ERROR, false);
        }
    }

    @RequestMapping(value = "qrcode", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ReceivingMoneyQRCode(@RequestParam String url) {
        try {
            String str = "www.baidu.com";
            String filename = "1.png";

            BitMatrix byteMatrix = new MultiFormatWriter().encode(new String(str.getBytes("UTF-8"), "iso-8859-1"),
                    BarcodeFormat.QR_CODE, 200, 200);
            File sf = new File("C:\\Users\\lixindi\\Desktop");
            if (!sf.exists()) {
                sf.mkdirs();
            }
            OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
            MatrixToImageWriter.writeToStream(byteMatrix, "png", os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
