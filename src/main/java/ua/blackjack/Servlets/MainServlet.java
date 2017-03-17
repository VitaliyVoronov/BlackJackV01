package ua.blackjack.Servlets;

import ua.blackjack.controller.Controller;

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
    Controller con = new Controller();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
        //out.println("<h1>" + "HI!" + "</h1>");
        //out.println("<h1>" + request.getRequestURI() + "</h1>");
        if(request.getRequestURI().equals("/")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);

        //TODO Take user from DB by name and password
        } else if (request.getRequestURI().equals("/enter")){

            if (con.checkNameAndPassword(request.getParameter("name"), request.getParameter("password"))) {
                con.getPlayerFromDB(request.getParameter("name"));
                con.setEnter(true);
                request.getSession().setAttribute("controller", con);
                String message = "User " + con.getPlayer().getName()+"; Email: "+con.getPlayer().getEmail();
                request.getSession().setAttribute("message", message);
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);

            } else {
                String message = "Incorrect login or password";
                request.setAttribute("message", message);
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }

        } else if (request.getRequestURI().equals("/registration")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);

        } else if (request.getRequestURI().equals("/signup")) {
            if (con.isAvailableName(request.getParameter("nameReg"))) {
                con.addPlayerToDB(request.getParameter("nameReg"),
                        request.getParameter("passwordReg"),
                        request.getParameter("emailReg"));
                request.setAttribute("message", "Registration completed successfully!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("message", "This name is busy!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
                dispatcher.forward(request, response);
            }

        } else if (request.getRequestURI().equals("/settings")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("settings.jsp");
            dispatcher.forward(request, response);

        } else if (request.getRequestURI().equals("/saveSettings")) {
            request.setAttribute("message", "Settings completed successfully!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("settings.jsp");
            dispatcher.forward(request, response);

        } else if (request.getRequestURI().equals("/game")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("game.jsp");
            dispatcher.forward(request, response);

        } else if (request.getRequestURI().equals("/login")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
