package ua.blackjack.servlets;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.blackjack.engine.Engine;
import ua.blackjack.model.Card;
import ua.blackjack.model.MySettings;
import ua.blackjack.model.Player;

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
    public ModelAndView login(){
        return new ModelAndView("login", "player", new Player());
    }

    @RequestMapping(value = "/checkPlayer", method = RequestMethod.POST)
    public ModelAndView checkPlayer(@ModelAttribute("player") Player player){
        if (engine.signIn(player.getName(), player.getPassword())){
            return new ModelAndView("menu","player", engine.getPlayer());
        } else {
            return new ModelAndView("login", "message", engine.getMessage());
        }
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ModelAndView menu(){
        return new ModelAndView("menu", "player", engine.getPlayer());
    }

    @RequestMapping(value = "/registrationForm", method = RequestMethod.GET)
    public ModelAndView registrationForm() {
        return new ModelAndView("registration", "player", new Player());
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ModelAndView signUp(@ModelAttribute("player") Player player) {
        engine.signUp(player.getName(),
                player.getPassword(),
                player.getEmail());
        return new ModelAndView("registration", "message", engine.getMessage());
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ModelAndView settings() {
        if (engine.isSignIn()) {
            return new ModelAndView("settings", "settings", new MySettings());
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/saveSettings", method = RequestMethod.POST)
    public ModelAndView saveSettings(@ModelAttribute("settings") MySettings settings) {
        engine.changeSettings(settings);
        return new ModelAndView("settings", "message", engine.getMessage());
    }

    @RequestMapping(value = "/startGame", method = RequestMethod.GET)
    public ModelAndView startGame(@ModelAttribute("settings") MySettings settings) {
        engine.startGame();
        return new ModelAndView("game", "message", engine.getMessage());
    }


    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String game(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (engine.isSignIn()) {
            Player player = engine.getPlayer();
            Player dealer = engine.getDealer();
            String query = "" + request.getQueryString();

//            if (query.equals("action=StartGame")) {
//                engine.startGame();
//            }

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
            request.setAttribute("message", engine.getMessage());
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

}
