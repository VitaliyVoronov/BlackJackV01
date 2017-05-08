package ua.blackjack.servlets;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

//    @RequestMapping(value = "/startGame", method = RequestMethod.GET)
//    public ModelAndView startGame(@ModelAttribute("settings") MySettings settings) {
//        engine.startGame();
//
//        return new ModelAndView("game", "message", engine.getMessage());
//    }


    @RequestMapping(value = "/game/{action:.+}", method = RequestMethod.GET)
    public ModelAndView game(@PathVariable("action") String action) {
        Player player = engine.getPlayer();
        Player dealer = engine.getDealer();
        ModelAndView model = new ModelAndView();


            if (action.equalsIgnoreCase("start")){
                engine.startGame();
            }

            if (action.equalsIgnoreCase("deal")) {
                engine.deal();
            }

            if (action.equalsIgnoreCase("newGame")) {
                engine.newGame();
            }

            if(!engine.isDealPushed() && engine.isNewGamePushed()) {
                if (action.equals("1")) {
                    engine.bet(1);
                } else if (action.equals("5")){
                    engine.bet(5);

                } else if (action.equals("10")) {
                    engine.bet(10);
                }
            }

            model.addObject("bet", engine.getBet());
            List<String> listShirt = new ArrayList<>();
            for (Card c : engine.getShoes()) {
                listShirt.add(c.getShirt());
            }
            model.addObject("shirts", listShirt);

            if (action.equalsIgnoreCase("stand") && engine.isGame()) {
                engine.stand();
            }

            if (engine.isGame() || !engine.isNewGamePushed()) {
                List<String> cardsDealer = new ArrayList<>();
                for (int i = 0; i < dealer.getHand().getCards().size(); i++) {
                    Card c = dealer.getHand().getCards().get(i);
                    cardsDealer.add(c.getFace());
                }
                model.addObject("nameDealer", dealer.getName());
                model.addObject("cardsDealer", cardsDealer);
                model.addObject("sumNumbersDealer", dealer.getSumNumbers());
            }

            if (action.equals("hit")) {
                engine.hit();
            }
            if (engine.isGame() || !engine.isNewGamePushed()) {
                List<String> cardsPlayer = new ArrayList<>();
                for (int i = 0; i < player.getHand().getCards().size(); i++) {
                    Card c = player.getHand().getCards().get(i);
                    cardsPlayer.add(c.getFace());
                    System.out.println(c.getFace());
                }
                model.addObject("cardsPlayer", cardsPlayer);
                model.addObject("sumNumbersPlayer", player.getSumNumbers());
            }
        model.addObject("namePlayer", player.getName());
        model.addObject("moneyPlayer", player.getMoney());
        model.addObject("playerSettings", player.getSettings().getInfoSettings());
        model.addObject("message", engine.getMessage());
        model.addObject("money", player.getMoney());
        model.addObject("settings", player.getSettings());

        model.setViewName("game");
        return model;
    }

}
