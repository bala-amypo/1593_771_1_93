// package com.example.demo.servlet;

// import jakarta.servlet.http.*;
// import jakarta.servlet.annotation.*;
// import java.io.IOException;

// @WebServlet("/hello-servlet")
// public class SimpleHelloServlet extends HttpServlet {

//     @Override
//     protected void doGet(
//             HttpServletRequest request,
//             HttpServletResponse response)
//             throws IOException {

//         response.setStatus(200);
//         response.setContentType("text/plain");
//         response.getWriter()
//                 .write("Hello from Simple Hello Servlet");
//     }
// }