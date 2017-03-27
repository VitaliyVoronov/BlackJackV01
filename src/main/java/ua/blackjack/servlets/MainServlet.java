package ua.blackjack.servlets;

import org.apache.log4j.Logger;
import ua.blackjack.engine.Engine;
import ua.blackjack.model.Card;
import ua.blackjack.model.Player;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/25/17
 */

public class MainServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(MainServlet.class);

    Engine engine = new Engine();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getRequestURI().equals("/main")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);

        //TODO Take user from DB by name and password
        } else if (request.getRequestURI().equals("/enter")){
            logger.trace("Try to enter: "+request.getParameter("name"));
            if (engine.signIn(request.getParameter("name"),request.getParameter("password"))){
//            if (engine.checkNameAndPassword(request.getParameter("name"), request.getParameter("password"))) {
//                engine.getPlayerFromDB(request.getParameter("name"));
                engine.setEnter(true);
                //TODO I do not like how it looks like
                engine.getSettingsFromXml(engine.getPlayer().getName());
                logger.trace("Entered: "+request.getParameter("name"));

                request.getSession().setAttribute("engine", engine);
                String message = "User " + engine.getPlayer().getName()+"; Email: "+ engine.getPlayer().getEmail();
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
            if (engine.isAvailableName(request.getParameter("nameReg"))) {
                engine.addPlayerToDB(request.getParameter("nameReg"),
                        request.getParameter("passwordReg"),
                        request.getParameter("emailReg"));
                request.setAttribute("message", "Registration completed successfully!");
                //TODO This have to do auto
                engine.setDefaultSettings();
                engine.saveNewSettingsToXML(engine.getPlayer().getSettings());

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
            engine.getPlayer().setSettingsParameters(decks,minBet,maxBet,money);
            engine.saveNewSettingsToXML(engine.getPlayer().getSettings());

            request.setAttribute("message", "Settings completed successfully!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("settings.jsp");
            dispatcher.forward(request, response);

        //Game jsp
        } else if (request.getRequestURI().equals("/game")) {

            Player player = engine.getPlayer();
            Player dealer = engine.getDealer();
            String query = ""+request.getQueryString();
            engine.getSettingsFromXml(player.getName());

            if (query.equals("action=DEAL") && engine.isContinuePushed()){
                engine.firstDealToAll();
                if (player.getHand().getCards().size() > 0 && dealer.getHand().getCards().size() > 0){
                    engine.setContinuePushed(false);
                    engine.setGameTrue();
                }
            }

            if(query.equals("action=CONTINUE") && !engine.isGame()){
                engine.setContinuePushed(true);
                engine.clearTable();
                engine.countWin();
                engine.clearBet();
                engine.clearPoints();
            }

            if (query.equals("bet=1") && !engine.isDealPushed() && engine.isContinuePushed()){
                engine.takeMoneyFromPlayerToBet(1);
            }
            if (query.equals("bet=5") && !engine.isDealPushed() && engine.isContinuePushed()){
                engine.takeMoneyFromPlayerToBet(5);

            }
            if (query.equals("bet=10") && !engine.isDealPushed() && engine.isContinuePushed()){
                engine.takeMoneyFromPlayerToBet(10);
            }

            request.setAttribute("bet", engine.getBet());
            List<String> listShirt = new ArrayList<>();
            for (Card c : engine.getShoes()){
                listShirt.add(c.getShirt());
            }
            request.setAttribute("shirts", listShirt);

            if(query.equals("action=STAND") && engine.isGame()){
                engine.dealCardsToDealer();
                engine.setGameFalse();
            }

            if (engine.isGame() || !engine.isContinuePushed()) {
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
                engine.dealOneCardToPlayer();
            }
            if (engine.isGame() || !engine.isContinuePushed()) {
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
            request.setAttribute("message",engine.getMassage());
            request.getSession().setAttribute("money", player.getMoney());

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
