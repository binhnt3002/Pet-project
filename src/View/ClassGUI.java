package View;

import Controller.ClassController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ClassGUI extends JFrame  {

    private JPanel topPanel;
    private JButton button;
    private JTextField inputField;
    private JLabel fCurrency,sCurrency;
    private JComboBox comboBox, comboBox2;

    private ClassController classController;
    private enum currency {
        USD,EUR,JPY,GBP,CNY,INR,AUD,VND
    }



    public ClassGUI() {
        super("Currency Converter");
        setSize(500,150);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);



        topPanel = new JPanel();
        fCurrency = new JLabel("Choose currency: ");
        sCurrency = new JLabel("Choose currency: ");
        comboBox = new JComboBox<>();
        for (currency currency : currency.values()) {
            comboBox.addItem(currency);
        }
        comboBox.setSelectedIndex(0);

        comboBox2 = new JComboBox<>();
        for (currency currency : currency.values()) {
            comboBox2.addItem(currency);
        }
        comboBox2.setSelectedIndex(1);

        topPanel.add(fCurrency);
        topPanel.add(comboBox);
        topPanel.add(sCurrency);
        topPanel.add(comboBox2);






        inputField = new JTextField();
        inputField.setFont(new Font("Arial",Font.BOLD,30));



        button = new JButton("Convert");
        button.setSize(JFrame.WIDTH,30);




        add(topPanel,BorderLayout.NORTH);
        add(inputField,BorderLayout.CENTER);
        add(button,BorderLayout.SOUTH);

        setVisible(true);

        checkInternetConnection(new ClassController().checkInternetConnection("www.google.com"));

        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String curentText = inputField.getText();
                if (!Character.isDigit(c) || curentText.length() >=9 ) {
                    e.consume();
                }
            }
        });

        button.addActionListener(e -> {
            classController = new ClassController();
            classController.getIOCurrencies(comboBox.getSelectedItem().toString(),comboBox2.getSelectedItem().toString(),Integer.parseInt(inputField.getText()));
            if (checkItemSelection() == false) {return;}
            int rs = classController.callFunctionCurrencyConvert(comboBox.getSelectedItem().toString(),comboBox2.getSelectedItem().toString(),Integer.parseInt(inputField.getText()));
            NumberFormat nf = NumberFormat.getInstance();
            JOptionPane.showMessageDialog(null, nf.format(rs) + " " + comboBox2.getSelectedItem());
        });

    }

    public void checkInternetConnection(boolean rs) {
        int choice = -1;
        Object[] options = {"Try Again", "Quit"};
        if (rs == false) {choice = JOptionPane.showOptionDialog(this,"No internet connection","Error",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);}
        switch (choice) {
            case 0:
                ClassGUI classGUI = new ClassGUI();
                break;
            case 1:
                System.exit(0);
        }
    }

    public boolean checkItemSelection() {
        if (comboBox.getSelectedItem() == comboBox2.getSelectedItem()) {
            JOptionPane.showMessageDialog(null, "Please select different currencies");
            return false;
        }
        return true;
    }
}
