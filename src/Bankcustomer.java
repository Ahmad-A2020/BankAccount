import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.SQLException;

public class Bankcustomer implements AccountManagement{

    private String name;
    private String CardNum;
    private String PassWord;
    private double ChequeBalance;
    private double SavingBalance;
    private boolean LoginStatus;
    private BufferedWriter writer;

    public Bankcustomer(String name, String cardNum, String passWord) throws SQLException {
        this.name = name;
        CardNum = cardNum;
        PassWord = passWord;
        ChequeBalance = GetChequeBalance();
        SavingBalance = GetSavingBalance();

        System.out.println(GetChequeBalance());
        System.out.println(GetSavingBalance());

        PrintAccountDetails();

    }
    private void PrintAccountDetails(){
        try{
            writer= new BufferedWriter(new FileWriter("Log.txt"));
            writer.write("Customer Name: "+ name);
            writer.newLine();
            writer.write("Card Number: "+CardNum );
            writer.newLine();
            writer.write("chequing account balance : "+ ChequeBalance);
            writer.newLine();
            writer.write("saving account balance : "+ SavingBalance);


            writer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public double GetChequeBalance() throws SQLException {

        return DbManager.getChequeBalance(CardNum);
    }

    @Override
    public double GetSavingBalance() throws SQLException {
        return DbManager.getSavingBalance(CardNum);
    }

    @Override
    public double Login() throws SQLException {
        LoginStatus=DbManager.validatePW(PassWord, CardNum);
        return 0;
    }
}
