package com.nhatnguyen.demoolop.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.awt.image.BufferedImage;

import cn.apiclub.captcha.Captcha;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javax.imageio.ImageIO;

@WebServlet(name = "CaptchaServlet",value = "/CaptchaServlet")
public class CaptchaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Create a new CAPTCHA
        Captcha captcha = new Captcha.Builder(250, 50)
                .addText()
                .addBackground()
                .addNoise()
                .build();

        // Store CAPTCHA in session
        HttpSession session = request.getSession();
        session.setAttribute(Captcha.NAME, captcha);

        // Create CAPTCHA image
        BufferedImage image = captcha.getImage();

        // Serve the CAPTCHA image
        response.setContentType("image/png");
        try (OutputStream outputStream = response.getOutputStream()) {
            ImageIO.write(image, "png", outputStream);
        }
    }
}