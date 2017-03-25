package ua.blackjack.servlets;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.blackjack.controller.Controller;
import ua.blackjack.model.Card;
import ua.blackjack.model.Player;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vitaliy
 * @version 1.0
 */
public class MainServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(MainServlet.class);

    Controller con = new Controller();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getRequestURI().equals("/main")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);

        //TODO Take user from DB by name and password
        } else if (request.getRequestURI().equals("/enter")){
            logger.trace("Try to enter: "+request.getParameter("name"));
            if (con.checkNameAndPassword(request.getParameter("name"), request.getParameter("password"))) {
                con.getPlayerFromDB(request.getParameter("name"));
                con.setEnter(true);
                //TODO I do not like how it looks like
                con.getSettingsFromXml(con.getPlayer().getName());
                logger.trace("Entered: "+request.getParameter("name"));
                logger.info("Test log info");
                logger.debug("Test log debug");
                logger.error("Test log error");
                logger.fatal("Test log fatal");




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
                //TODO This have to do auto
                con.setDefaultSettings();
                con.saveNewSettingsToXML(con.getPlayer().getSettings());

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
            int decks = Integer.parseInt(request.getParameter("decks"));
            int minBet = Integer.parseInt(request.getParameter("minBet"));
            int maxBet = Integer.parseInt(request.getParameter("maxBet"));
            int money = Integer.parseInt(request.getParameter("moneySet"));
            //TODO This have to do auto
            con.getPlayer().setSettingsParameters(decks,minBet,maxBet,money);
            con.saveNewSettingsToXML(con.getPlayer().getSettings());

            request.setAttribute("message", "Settings completed successfully!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("settings.jsp");
            dispatcher.forward(request, response);

        //Game jsp
        } else if (request.getRequestURI().equals("/game")) {

            Player player = con.getPlayer();
            Player dealer = con.getDealer();
            String query = ""+request.getQueryString();
            con.getSettingsFromXml(player.getName());

            if (query.equals("action=DEAL") && con.isContinuePushed()){
                con.firstDealToAll();
                if (player.getHand().getCards().size() > 0 && dealer.getHand().getCards().size() > 0){
                    con.setContinuePushed(false);
                    con.setGameTrue();
                }
            }

            if(query.equals("action=CONTINUE") && !con.isGame()){
                con.setContinuePushed(true);
                con.clearTable();
                con.countWin();
                con.clearBet();
                con.clearPoints();
            }

            if (query.equals("bet=1") && !con.isDealPushed() && con.isContinuePushed()){
                con.takeMoneyFromPlayerToBet(1);
            }
            if (query.equals("bet=5") && !con.isDealPushed() && con.isContinuePushed()){
                con.takeMoneyFromPlayerToBet(5);

            }
            if (query.equals("bet=10") && !con.isDealPushed() && con.isContinuePushed()){
                con.takeMoneyFromPlayerToBet(10);
            }

            request.setAttribute("bet", con.getBet());
            List<String> listShirt = new ArrayList<>();
            for (Card c : con.getShoes()){
                listShirt.add(c.getShirt());
            }
            request.setAttribute("shirts", listShirt);

            if(query.equals("action=STAND") && con.isGame()){
                con.dealCardsToDealer();
                con.setGameFalse();
            }

            if (con.isGame() || !con.isContinuePushed()) {
                List<String> cardsDealer = new ArrayList<>();
                for (int i = 0; i < dealer.getHand().getCards().size(); i++) {
                    Card c = dealer.getHand().getCards().get(i);
                    cardsDealer.add(c.getFace());
                }
                request.setAttribute("nameDealer", dealer.getName());
                request.setAttribute("cardsDealer",cardsDealer);
                request.setAttribute("sumNumbersDealer",dealer.getSumNumbers());
            }

            if (query.equals("action=HIT")) {
                con.dealOneCardToPlayer();
            }
            if (con.isGame() || !con.isContinuePushed()) {
                List<String> cardsPlayer = new ArrayList<>();
                for (int i = 0; i < player.getHand().getCards().size(); i++) {
                    Card c = player.getHand().getCards().get(i);
                    cardsPlayer.add(c.getFace());
                }
                request.setAttribute("namePlayer", player.getName());
                request.setAttribute("cardsPlayer", cardsPlayer);
                request.setAttribute("sumNumbersPlayer", player.getSumNumbers());
                request.setAttribute("moneyPlayer", player.getMoney());
            }
            request.setAttribute("message",con.getMassage());
            request.getSession().setAttribute("money", player.getMoney());

            RequestDispatcher dispatcher = request.getRequestDispatcher("game.jsp");
            dispatcher.forward(request, response);

        } else if (request.getRequestURI().equals("/login")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);

//        } else if (request.getRequestURI().equals("/test")) {
//
//            PrintWriter out = response.getWriter();
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>BlackJack</title>");
//            out.println("</head>");
//            out.println("<body>");
//            for (int i = 0; i < con.getShoes().size(); i++) {
//                Card c = con.getShoes().get(i);
//                System.out.println(con.getShoes());
//                out.println("<img src='resources/shirt.png' alt='card' class='shoes' />");
//            }
//            out.println("</body>");
//            out.println("</html>");

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
