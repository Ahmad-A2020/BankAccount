import java.sql.*;
import java.util.ArrayList;

public class DbManager {

    public static ArrayList<String> validateCard(String CardNum) throws SQLException {
        System.out.println(CardNum);
        ArrayList<String> accountDetail= new ArrayList<>();
        final String URL= "jdbc:derby:bank2";
        Connection conn = DriverManager.getConnection(URL);

        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(
                "SELECT * " +
                        "FROM Account " +
                        "WHERE CardNum = '" + CardNum + "'");

        if(resultSet.next()){
            accountDetail.add(resultSet.getString(1));
            accountDetail.add(resultSet.getString(2));
            accountDetail.add(resultSet.getString(3));
            accountDetail.add(resultSet.getDouble(4)+"");
            accountDetail.add(resultSet.getDouble(5)+"");
        }
        stmt.close();
        conn.close();
        return accountDetail;
    }

    public static boolean validatePW(String cardPIN, String CardNum) throws SQLException {
        System.out.println(cardPIN);
        ArrayList<String> accountDetail= new ArrayList<>();
        final String URL= "jdbc:derby:bank2";
        Connection conn = DriverManager.getConnection(URL);

        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(
                "SELECT * " +
                        "FROM Account " +
                        "WHERE CardNum = '" + CardNum + "'");

        if(resultSet.next()){
            accountDetail.add(resultSet.getString(1));
            accountDetail.add(resultSet.getString(2));
            accountDetail.add(resultSet.getString(3));
            accountDetail.add(resultSet.getDouble(4)+"");
            accountDetail.add(resultSet.getDouble(5)+"");
            if(accountDetail.get(2).substring(0,9).equals(cardPIN)){
                return true;
            }
        }
        stmt.close();
        conn.close();

        return false;
    }
    public static double getChequeBalance(String CardNum) throws SQLException {

        Double balance= 0.0;
        final String URL= "jdbc:derby:bank2";
        Connection conn = DriverManager.getConnection(URL);

        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(
                "SELECT * " +
                        "FROM Account " +
                        "WHERE CardNum = '" + CardNum + "'");

        if(resultSet.next()){

            balance=resultSet.getDouble(4);

        }
        stmt.close();
        conn.close();
        return balance;
    }

    public static double getSavingBalance(String CardNum) throws SQLException {

        Double balance= 0.0;
        final String URL= "jdbc:derby:bank2";
        Connection conn = DriverManager.getConnection(URL);

        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(
                "SELECT * " +
                        "FROM Account " +
                        "WHERE CardNum = '" + CardNum + "'");

        if(resultSet.next()){

            balance=resultSet.getDouble(5);

        }
        stmt.close();
        conn.close();
        return balance;
    }


}
