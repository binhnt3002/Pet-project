package View;

import Controller.ClassController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Objects;

public class ClassGUI extends JFrame  {

    private final JTextField inputField;
    private final JComboBox<Object> comboBox;
    private final JComboBox<Object> comboBox2;

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


        JPanel topPanel = new JPanel();
        JLabel fCurrency = new JLabel("Choose currency: ");
        JLabel sCurrency = new JLabel("Choose currency: ");
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


        JButton button = new JButton("Convert");
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
            inputField.setEditable(false);
            classController = new ClassController();
            classController.getIOCurrencies(Objects.requireNonNull(comboBox.getSelectedItem()).toString(), Objects.requireNonNull(comboBox2.getSelectedItem()).toString(),Integer.parseInt(inputField.getText()));
            if (!checkItemSelection()) {return;}
            int rs = classController.callFunctionCurrencyConvert(comboBox.getSelectedItem().toString(),comboBox2.getSelectedItem().toString(),Integer.parseInt(inputField.getText()));
            NumberFormat nf = NumberFormat.getInstance();
            JOptionPane.showMessageDialog(null, nf.format(rs) + " " + comboBox2.getSelectedItem());
        });

    }

    public void checkInternetConnection(boolean rs) {
        int choice = -1;
        Object[] options = {"Try Again", "Quit"};
        if (!rs) {choice = JOptionPane.showOptionDialog(this,"No internet connection","Error",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);}
        switch (choice) {
            case 0:
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
