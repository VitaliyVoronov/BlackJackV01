package ua.blackjack.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 03.11.2016.
 */
@WebServlet(name = "MyServlet")
public class MyServlet extends HttpServlet {
    //Controller con = new Controller();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setAttribute("con",con);


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>" + "Hello servlet" + "</h1>");
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i<100;i++ ){
            list.add(i);
        }
        System.out.println(list.size());
        request.getSession().setAttribute("list",list);

    }
}
