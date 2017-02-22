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
 * @since 1/12/17
 */

public class RegistrationServlet extends HttpServlet {

    private Controller con = new Controller();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        con.addPlayerToDB(request.getParameter("nameReg"),request.getParameter("passwordReg"),
                request.getParameter("emailReg"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
        dispatcher.forward(request,response);
    }

}
