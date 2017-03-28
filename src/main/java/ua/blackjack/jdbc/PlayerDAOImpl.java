package ua.blackjack.jdbc;
import org.apache.log4j.Logger;
import ua.blackjack.engine.Engine;
import ua.blackjack.model.Player;
import ua.blackjack.model.PlayerDAO;

import java.sql.*;

/**
 * Created by Администратор on 08.11.2016.
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
            st = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/blackjack?user=root&password=12345");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }

    public boolean addPlayerToDB(String name, String password, String email){
        try {
            st.execute("INSERT INTO players(name,password,email) VALUES('" + name.trim() + "','"
                    + password + "','" + email + "')");
            return true;
        } catch (SQLException e) {
            logger.error("Problem with add player to db!",e);
        }
        return false;
    }

    public boolean isAvailableName(String name) {
        String str = "";
        ResultSet rs = null;
        try {
            rs = st.executeQuery("SELECT name FROM players WHERE name = '"+name.trim()+"'");
            while (rs.next()) {
                str += rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (str.equals("")){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkNameAndPassword(String name, String password){
        String str = "";
        try {
            ResultSet rs = st.executeQuery("SELECT name FROM players WHERE name = '"+name+"' AND password ='"+password+"'");
            while (rs.next()) {
                str = rs.getString(1);
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!str.equals("")){
            return true;
        } else {
            return false;
        }

    }

    public Player getPlayerFromDB(String name) throws SQLException {
        Player player = new Player();
        ResultSet rs = st.executeQuery("SELECT * FROM players WHERE name = '"+name+"'");
        while (rs.next()) {
            player.setName(rs.getString(2));
            player.setPassword(rs.getString(3));
            player.setEmail(rs.getString(4));
        }
        rs.close();
        return player;
    }

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

    public void closeConnection() {
        try {
            st.close();
            con.close();
//            connector.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

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

    //return player from db or null if no player with this name or password
    @Override
    public Player getPlayerByNameAndPassword(String name, String password) {
        Player player = null;
        ResultSet rs;
        try {
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
            e.printStackTrace();
        }

        return player;
    }

    @Override
    public boolean updatePlayer(Player player) {
        int numChangeColumn = 0;
        try {
            numChangeColumn = st.executeUpdate(UPDATE+"SET `name`='"+player.getName()+"" +
                    "', `password`='"+player.getPassword()+"', `email`='"+player.getEmail()+"" +
                    "' WHERE `id`='"+player.getPlayerID()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (numChangeColumn > 0 ){
            return true;
        } else {
            return false;
        }
    }
}