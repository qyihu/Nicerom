package com.qyihu.nicerom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Controller
@RequestMapping("test")
public class TestEventSource {

    @RequestMapping(value = "goTestPage", method = RequestMethod.GET)
    public String goTestPage() {
        return "redirect:/index.html";
    }

    @ResponseBody
    @RequestMapping(value = "eventSource", method = RequestMethod.GET)
    public void eventSource(HttpServletResponse response) throws IOException {
        //媒体类型为 text/event-stream
        response.setContentType("text/event-stream;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");
        PrintWriter out = response.getWriter();
        while (true) {
            out.print("id: " + "ServerTime" + "\n");
            out.print("data: " + new Date().toString() + "\n\n");
            out.flush();
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
