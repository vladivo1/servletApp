package com.example.demo.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String log = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        Map<String,String> usersMap = new HashMap<>();
        usersMap.put(log,pwd);

        for (Map.Entry<String,String> kv: usersMap.entrySet()) {
           if (kv.getValue().equals(pwd)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", "user");
                //setting session to expiry in 30 min
                session.setMaxInactiveInterval(30 * 60);
                Cookie userName = new Cookie("user", log);
                userName.setMaxAge(30 * 60);
                response.addCookie(userName);
                PrintWriter out = response.getWriter();
                out.println("Welcome back to the team, " + log + "!" + "You using Map for save your login and password!");
            } else {
                PrintWriter out = response.getWriter();
                out.println("Either user name or password is wrong!");
            }
        }
            }
    }



