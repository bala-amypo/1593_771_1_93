package com.example.demo.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class SimpleHelloServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        response.setContentType("text/plain");
        response.getWriter().write("Hello from SimpleHelloServlet");
    }
}


// <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
// <dependency>
//     <groupId>org.projectlombok</groupId>
//     <artifactId>lombok</artifactId>
//     <version>1.18.42</version>
// </dependency>