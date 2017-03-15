package ua.blackjack.model;

/**
 * @author vitaliy
 * @version 1.0
 */
public interface PlayerDAO {

    public boolean createPlayer(Player player);
    public boolean deletePlayer(Player player);
    public Player getPlayerByNameAndPassword(String name, String password);
    public boolean updatePlayer(Player player);


}
