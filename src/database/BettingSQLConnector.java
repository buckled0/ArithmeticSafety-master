package database;

import domain.Betting;

import java.sql.*;
import java.util.ArrayList;

public class BettingSQLConnector {

    private String tableName;
    private String url = "jdbc:mysql://localhost:3306/test";
    private String driver = "com.mysql.jdbc.Driver";
    private String userName = "daniel";
    private String password = "JBaxter2011";
    private Connection connection = null;
    private Statement st = null;
    private ResultSet res = null;
    public ArrayList<Betting> bettingList = new ArrayList<Betting>();


    public BettingSQLConnector(){

        try {

            Class.forName(driver);

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your MySQL JDBC Driver?");

            e.printStackTrace();

            return;

        }

        System.out.println("Established a connection with " + tableName + " table");

        try {

            connection = DriverManager.getConnection(url, userName, password);
            st = connection.createStatement();
            res = st.executeQuery("select * from test.Premier_League ORDER BY Points DESC, Goal_Difference DESC;");
            while(res.next()){
                bettingList.add(new Betting(res.getString("Team_Name"), res.getInt("Last_6_games")));
            }

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");

            e.printStackTrace();

        } finally {
            try { res.close(); } catch (SQLException e) {   }
            try { st.close(); } catch (SQLException e) {   }
            try { connection.close(); } catch (SQLException e) {   }
        }

    }

    public ArrayList<Betting> getBettingList(){
        return bettingList;
    }


}

