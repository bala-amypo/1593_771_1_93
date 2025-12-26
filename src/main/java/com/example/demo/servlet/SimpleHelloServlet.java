package com.example.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SimpleHelloServlet", urlPatterns = {"/hello"})
public class SimpleHelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Set the response content type to text/plain
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        // Write a simple "Hello, World!" message
        PrintWriter out = resp.getWriter();
        out.println("Hello, World!");
        out.flush();
    }
}
