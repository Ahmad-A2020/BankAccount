import java.sql.SQLException;

public interface AccountManagement {
    double GetChequeBalance() throws SQLException;
    double GetSavingBalance() throws SQLException;
    double Login() throws SQLException;
}
