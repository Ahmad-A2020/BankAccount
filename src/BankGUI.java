import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class BankGUI extends JFrame {
        private int PinORCard;
        String UserCard="";
        String UserPassWord="";
        private int tray=0;
        private ArrayList<String> lockedCard= new ArrayList<>();
        private ArrayList<String> accountDetails= new ArrayList<>();
    public BankGUI() throws HeadlessException {
         PinORCard=0;

        JPanel headerPanel= new JPanel();
        JLabel label1= new JLabel("Enter your card number");
        JTextField field1 = new JTextField(10);
        headerPanel.setLayout(new FlowLayout());
        headerPanel.add(label1);
        headerPanel.add(field1);

        JPanel bodyPanel= new JPanel();
        bodyPanel.setLayout(new GridLayout(4,3));
        ArrayList<JButton> buttons = new ArrayList<>();
        for(int i=1;i<=10;i++){
            JButton button = new JButton(i+"");
            buttons.add(button);
            bodyPanel.add(button);
        }

        JButton b0= new JButton("0");
        buttons.add(b0);
        JButton enter= new JButton("Enter");
        JButton clear= new JButton("Clear");

        bodyPanel.add(b0);
        bodyPanel.add(enter);
        bodyPanel.add(clear);

        // add listeners

        for(JButton button:buttons){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    field1.setText(field1.getText()+button.getText());
                }
            });
        }

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field1.setText("");
            }
        });

        enter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(PinORCard==0){
                    PinORCard=1;
                    try {
                        accountDetails = DbManager.validateCard(field1.getText());
                        if( accountDetails.size()==0 || field1.getText().length()<10){
                            JFrame f= new JFrame();
                            JOptionPane.showMessageDialog(f, "Card number is invalid, please input again");
                        }else {
                            UserCard= field1.getText();
                            label1.setText("Enter the PIN number:");

                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }else {
                    tray++;
                    if(tray>3){
                        JFrame f= new JFrame();
                        lockedCard.add(UserCard);
                        JOptionPane.showMessageDialog(f, "Card is locked");
//                        System.exit();
                    }else {
                        System.out.println(accountDetails.get(1).substring(0,9));
                        System.out.println(accountDetails.get(1).length());
                        System.out.println(field1.getText()+"999");
                        System.out.println((field1.getText()+"999").length());
                        System.out.println((accountDetails.get(1).toString().equalsIgnoreCase((field1.getText()+"999").toString())));
                           if( ! (accountDetails.get(1).substring(0,9).equals(field1.getText()+"999"))){
                               JFrame f= new JFrame();
                               JOptionPane.showMessageDialog(f, "wrong PIN");
//
                           }else{
                               try {
                                   Bankcustomer bankcustomer= new Bankcustomer(accountDetails.get(2),
                                           accountDetails.get(0), accountDetails.get(1));
                               } catch (SQLException ex) {
                                   ex.printStackTrace();
                               }
                           }


                    }


                }

            }
        });

        add(headerPanel, BorderLayout.NORTH);
        add(bodyPanel, BorderLayout.CENTER);


    }
}
