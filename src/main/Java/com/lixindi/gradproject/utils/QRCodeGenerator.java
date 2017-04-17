package com.lixindi.gradproject.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by lixindi on 2017/3/29.
 */
public class QRCodeGenerator {
    public static void GenerateQRCode(int num, String address) {
        try {
            File sf = new File("C:\\Users\\Administrator\\Desktop\\qrcode");
            if (!sf.exists()) {
                sf.mkdirs();
            }
            for (int i = 1; i <= num; i++) {
                String token = GetMD5.getMD5("token" + i);
                String url = address + "?id=" + i + "&token=" + token;
                BitMatrix byteMatrix = new MultiFormatWriter().encode(new String(url.getBytes("UTF-8"), "iso-8859-1"),
                        BarcodeFormat.QR_CODE, 300, 300);
                String filename = i + ".png";
                OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
                MatrixToImageWriter.writeToStream(byteMatrix, "png", os);
                os.close();
                System.out.println(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
