import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BankDB {
    public static void main(String[] args) throws SQLException {
        final String URL= "jdbc:derby:bank2; create=true";
        Connection conn = DriverManager.getConnection(URL);

        Statement stmt = conn.createStatement();
        // Create the table.
        stmt.execute("CREATE  TABLE Account(" +
                "CardNum	Char(10)	NOT NULL, " +
                "PassWord	Char(25)	NOT NULL, " +
                "UserName	Char(25)	NOT NULL, " +
                "ChequeBanlance	Numeric(15,2)    NOT NULL, " +
                "SavingBanlance	Numeric(15,2) " +
                ")");

        stmt.execute("INSERT INTO Account VALUES('0123456789', '123456999', 'Abc Test1',10900,24000)");
        stmt.execute("INSERT INTO Account VALUES('0345678912', '987654999', 'Def Test2',12900,14000)");
        stmt.execute("INSERT INTO Account VALUES('0654321987', '963852999', 'Hij Test3',22900,34000)");
        stmt.execute("INSERT INTO Account VALUES('0654321987', '963852999', 'Lmn Test4',22900,34000)");

        stmt.close();
        conn.close();


    }
}
