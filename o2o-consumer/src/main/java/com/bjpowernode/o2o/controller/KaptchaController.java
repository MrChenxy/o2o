package com.bjpowernode.o2o.controller;

import com.bjpowernode.o2o.conf.KaptchaConfig;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;


/**
 * @author Mr.chenxy
 * @date 2021/1/27
 */
@Controller
public class KaptchaController {

    @Autowired
    private KaptchaConfig kaptchaConfig;

    @RequestMapping("/conf/KaptchaConfig")
    public void kaptchaConfig(HttpServletResponse httpServletResponse, HttpSession session) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

        DefaultKaptcha defaultKaptcha = kaptchaConfig.getDefaultKaptcha();
        String text = defaultKaptcha.createText();
        session.setAttribute("KAPTCHA_CODE", text);
        BufferedImage challenge  = defaultKaptcha.createImage(text);

        ImageIO.write(challenge, "jpg", jpegOutputStream);

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

}
