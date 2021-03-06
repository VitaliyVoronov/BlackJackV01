package ua.blackjack.jdbc;
import org.apache.log4j.Logger;
import ua.blackjack.model.Player;
import ua.blackjack.model.PlayerDAO;

import java.sql.*;

/**
 * This class work with DB
 * @author vitaliy
 * @project BlackJackV01
 * @since 3/25/17
 */
public class PlayerDAOImpl implements PlayerDAO {

    final static Logger logger = Logger.getLogger(PlayerDAOImpl.class);

    private JdbcConnector connector;
    private Connection con;
    private Statement st;

    private final String INSERT = "INSERT INTO players(name,password,email) ";
    private final String DELETE = "DELETE FROM blackjack.players ";
    private final String SELECT = "SELECT * FROM blackjack.players ";
    private final String UPDATE = "UPDATE blackjack.players ";

    public PlayerDAOImpl() {

        connector = new JdbcConnector();
        con = connector.getConnection();
        try {
            logger.trace("Try to create statement");
            st = con.createStatement();
            logger.trace("Statement created");
        } catch (SQLException e) {
            logger.error("Statement did not create!",e);
        }

    }

    /**
     * Add new player to Db
     * @param login
     * @param password
     * @param email
     * @return true if added success and false if no
     */
    public boolean addPlayerToDB(String login, String password, String email){
        try {
            st.execute("INSERT INTO players(name,password,email) VALUES('" + login + "','"
                    + password + "','" + email + "')");
            return true;
        } catch (SQLException e) {
            logger.error("Problem with add player to db!",e);
        }
        return false;
    }

    /**
     * Check if login is free
     * @param login
     * @return true if login is free and false if busyup Founder track and assigned to a group.
     */
    public boolean isAvailableName(String login) {
        String str = "";
        ResultSet rs = null;
        try {
            logger.trace("Use executeQuery method from statement");
            rs = st.executeQuery("SELECT name FROM players WHERE name = '"+login.trim()+"'");
            while (rs.next()) {
                str += rs.getString(1);
            }
        } catch (SQLException e) {
            logger.error("Problem with statement",e);
        }
        if (str.equals("")){
            return true;
        } else {
            return false;
        }
    }

//    public boolean checkNameAndPassword(String name, String password){
//        String str = "";
//        try {
//            ResultSet rs = st.executeQuery("SELECT name FROM players WHERE name = '"+name+"' AND password ='"+password+"'");
//            while (rs.next()) {
//                str = rs.getString(1);
//                System.out.println(rs.getString(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (!str.equals("")){
//            return true;
//        } else {
//            return false;
//        }
//
//    }

//    public Player getPlayerFromDB(String name) throws SQLException {
//        Player player = new Player();
//        ResultSet rs = st.executeQuery("SELECT * FROM players WHERE name = '"+name+"'");
//        while (rs.next()) {
//            player.setName(rs.getString(2));
//            player.setPassword(rs.getString(3));
//            player.setEmail(rs.getString(4));
//        }
//        rs.close();
//        return player;
//    }

//    public void getAllFromDB(){
//        try {
//            ResultSet rs = st.executeQuery("SELECT * FROM players");
//            while (rs.next()){
//                System.out.println("id: "+rs.getInt(1)+"name: "+rs.getString(2)+"password: "+ rs.getString(3));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public void closeConnection() {
//        try {
//            st.close();
//            con.close();
////            connector.closeConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//    }

    @Override
    public boolean createPlayer(Player player) {
        int numChangeColumn = 0;
        try {
            numChangeColumn = st.executeUpdate(INSERT+"VALUES ('" + player.getName() + "','"
                    + player.getPassword() + "','" + player.getEmail() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (numChangeColumn > 0 ){
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean deletePlayer(Player player) {
        int numChangeColumn = 0;
        try {
            numChangeColumn = st.executeUpdate(DELETE+"WHERE `id`='"+player.getPlayerID()+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (numChangeColumn > 0 ){
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param name
     * @param password
     * @return player from db or null if no player with this name or password
     */
    @Override
    public Player getPlayerByNameAndPassword(String name, String password) {
        Player player = null;
        ResultSet rs;
        try {
            logger.trace("Use executeQuery method from statement");
            rs = st.executeQuery(SELECT+"WHERE name = '"+name+"'"+"AND password = '"+password+"'");
            while (rs.next()) {
                player = new Player();
                player.setPlayerID(rs.getInt(1));
                player.setName(rs.getString(2));
                player.setPassword(rs.getString(3));
                player.setEmail(rs.getString(4));
            }
            rs.close();
        } catch (SQLException e) {
            logger.error("Problem with statement",e);
        }
        return player;
    }

    /**
     * Update player info in DB
     * @param player
     * @return true if update was successful and false if not
     */
    @Override
    public boolean updatePlayer(Player player) {
        int numChangeColumn = 0;
        try {
            logger.trace("Try to update player in DB");
            numChangeColumn = st.executeUpdate(UPDATE+"SET `name`='"+player.getName()+"" +
                    "', `password`='"+player.getPassword()+"', `email`='"+player.getEmail()+"" +
                    "' WHERE `id`='"+player.getPlayerID()+"'");
        } catch (SQLException e) {
            logger.error("Problem with statement",e);
        }

        if (numChangeColumn > 0 ){
            return true;
        } else {
            return false;
        }
    }
}