package ua.blackjack.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author vitaliy
 * @version 1.0
 */
public class MainServlet extends HttpServlet {
    //Controller con = new Controller();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*PrintWriter out = response.getWriter();
        out.println("<h1>" + "HI!" + "</h1>");
        out.println("<h1>" + request.getRequestURI() + "</h1>");*/
        if(request.getRequestURI().equals("/main")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } else if (request.getParameter("action").equals("Registration")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
