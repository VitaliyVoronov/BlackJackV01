package ua.blackjack.servlets;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.blackjack.engine.Engine;
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
 * @project BlackJackV01
 * @since 3/25/17
 */
@Controller
public class MainServlet extends HttpServlet {

    final static Logger logger = Logger.getLogger(MainServlet.class);

    private Engine engine = new Engine();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if(request.getRequestURI().equals("/main")) {
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//            dispatcher.forward(request, response);
//
//        } else if (request.getRequestURI().equals("/signIn")){
//            signIn(request, response);
//
//        } else if (request.getRequestURI().equals("/registration")){
//            RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
//            dispatcher.forward(request, response);
//
//        } else if (request.getRequestURI().equals("/signup")) {
//            signUp(request, response);
//
//        } else if (request.getRequestURI().equals("/settings")) {
//            settings(request,response);
//
//        } else if (request.getRequestURI().equals("/saveSettings")) {
//            saveSettings(request,response);
//
//        //Game jsp
//        } else if (request.getRequestURI().equals("/game")) {
//            game(request,response);
//
//        } else if (request.getRequestURI().equals("/login")) {
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//            dispatcher.forward(request, response);
//
//        }
//    }

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.trace("Try to sign in: "+request.getParameter("name"));
        if (engine.signIn(request.getParameter("name"),request.getParameter("password"))){
            logger.trace("Entered: "+request.getParameter("name"));
            //TODO Why I transfer engine?
            //request.getSession().setAttribute("engine", engine);
            String message = "User " + engine.getPlayer().getName()+"; Email: "+ engine.getPlayer().getEmail();
            request.getSession().setAttribute("message", message);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//            dispatcher.forward(request, response);
            return "login";

        } else {
            String message = "Incorrect login or password";
            request.setAttribute("message", message);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//            dispatcher.forward(request, response);
            return "login";
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        engine.signUp(request.getParameter("nameReg"),
                request.getParameter("  passwordReg"),
                request.getParameter("emailReg"));
        request.setAttribute("message", engine.getMassage());

//        RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
//        dispatcher.forward(request, response);

        return "registration";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "";
        if (engine.isSignIn()) {
            message = "Fill in all the fields.";
            request.setAttribute("message", message);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("settings.jsp");
//            dispatcher.forward(request, response);
            return "settings";
        } else {
            message = "No sign in!";
            request.setAttribute("message", message);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//            dispatcher.forward(request, response);
            return "login";
        }
    }

    @RequestMapping(value = "/saveSettings", method = RequestMethod.GET)
    public String saveSettings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "";
        int decks = Integer.parseInt(request.getParameter("decks"));
        int minBet = Integer.parseInt(request.getParameter("minBet"));
        int maxBet = Integer.parseInt(request.getParameter("maxBet"));
        int money = Integer.parseInt(request.getParameter("moneySet"));

        if (decks != 0 && minBet != 0 && maxBet != 0 && money != 0){
            logger.debug("Try to change settings!");
            engine.changeSettings(decks,minBet,maxBet,money);
            logger.debug("Settings changed!");
            message = "Settings completed successfully!";
        } else {
            message = "Fill in all the fields.";
        }

        request.setAttribute("message", message);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("settings.jsp");
//        dispatcher.forward(request, response);
        return "settings";
    }

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String game(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (engine.isSignIn()) {
            Player player = engine.getPlayer();
            Player dealer = engine.getDealer();
            String query = "" + request.getQueryString();

            if (query.equals("action=StartGame")) {
                engine.startGame();
            }

            if (query.equals("action=DEAL")) {
                engine.deal();
            }

            if (query.equals("action=New+game")) {
                engine.newGame();
            }

            if(!engine.isDealPushed() && engine.isNewGamePushed()) {
                if (query.equals("bet=1")) {
                    engine.bet(1);
                } else if (query.equals("bet=5")){
                    engine.bet(5);

                } else if (query.equals("bet=10")) {
                    engine.bet(10);
                }
            }

            request.setAttribute("bet", engine.getBet());
            List<String> listShirt = new ArrayList<>();
            for (Card c : engine.getShoes()) {
                listShirt.add(c.getShirt());
            }
            request.setAttribute("shirts", listShirt);

            if (query.equals("action=STAND") && engine.isGame()) {
                engine.stand();
            }

            if (engine.isGame() || !engine.isNewGamePushed()) {
                List<String> cardsDealer = new ArrayList<>();
                for (int i = 0; i < dealer.getHand().getCards().size(); i++) {
                    Card c = dealer.getHand().getCards().get(i);
                    cardsDealer.add(c.getFace());
                }
                request.setAttribute("nameDealer", dealer.getName());
                request.setAttribute("cardsDealer", cardsDealer);
                request.setAttribute("sumNumbersDealer", dealer.getSumNumbers());
            }

            if (query.equals("action=HIT")) {
                engine.hit();
            }
            if (engine.isGame() || !engine.isNewGamePushed()) {
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
            request.setAttribute("playerSettings", player.getSettings().getInfoSettings());
            request.setAttribute("message", engine.getMassage());
            request.getSession().setAttribute("money", player.getMoney());

//            RequestDispatcher dispatcher = request.getRequestDispatcher("game.jsp");
//            dispatcher.forward(request, response);
            return "game";
        } else {
            String message = "No sign in!";
            request.setAttribute("message", message);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//            dispatcher.forward(request, response);
            return "login";
        }
    }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
}
