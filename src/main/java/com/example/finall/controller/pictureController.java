package com.example.finall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
public class pictureController {
    @GetMapping("/static/**")
    public void handleStaticResource(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String uri = request.getRequestURI();
            String path = "/static" + uri.substring("/static".length());
            InputStream inputStream = getClass().getResourceAsStream(path);
            if (inputStream == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
        }

}
