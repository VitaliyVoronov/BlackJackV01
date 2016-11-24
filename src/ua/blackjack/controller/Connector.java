package ua.blackjack.controller;
import ua.blackjack.model.Player;

import java.sql.*;

/**
 * Created by Администратор on 08.11.2016.
 */
public class Connector {

    Connection con ;
    Statement st ;

    public Connector() {
        try {
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
        }

    }

    public void addPlayerToDB(String name, String password, String email){
        try {
            st.execute("INSERT INTO players(name,password,email) VALUES('" + name.trim() + "','"
                    + password + "','" + email + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    public void getAllFromDB(){
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM players");
            while (rs.next()){
                System.out.println("id: "+rs.getInt(1)+"name: "+rs.getString(2)+"password: "+ rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    public void closeConnection() {

        try {
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}