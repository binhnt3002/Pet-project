package com.petproject.view;
import javax.swing.*;

import com.petproject.controller.ClassController;

import java.awt.*;
import java.text.NumberFormat;
import java.util.Objects;

public class GUI extends JFrame  {

    private final JTextField inputField;
    private final JComboBox<Object> comboBox;
    private final JComboBox<Object> comboBox2;

    private static ClassController classController;
    private NumericTextField numberfield;
    private enum currency {
        USD,EUR,JPY,GBP,CNY,INR,AUD,VND
    }

    static{
        classController = new ClassController();
        if (classController.getApi_key() == null) {
            fieldGetApiKey();
        }
    }



    public GUI() {
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
        numberfield = new NumericTextField();
        numberfield.setFont(new Font("Arial",Font.BOLD,30));

        JButton button = new JButton("Convert");
        button.setSize(JFrame.WIDTH,30);


        add(topPanel,BorderLayout.NORTH);
        add(numberfield,BorderLayout.CENTER);
        add(button,BorderLayout.SOUTH);

        setVisible(true);

        checkInternetConnection(classController.checkInternetConnection("www.google.com"));   



        button.addActionListener(e -> {
            inputField.setEditable(false);
            classController.getIOCurrencies(String.valueOf(comboBox.getSelectedItem()), String.valueOf(comboBox2.getSelectedItem()),Integer.valueOf(numberfield.getText()));
            if (!checkItemSelection()) {return;}
            int rs = classController.callFunctionCurrencyConvert(String.valueOf(comboBox.getSelectedItem()), String.valueOf(comboBox2.getSelectedItem()),Integer.valueOf(numberfield.getText()));
            NumberFormat nf = NumberFormat.getInstance();
            System.out.println(nf.format(rs));
            JOptionPane.showMessageDialog(null, nf.format(rs) + " " + comboBox2.getSelectedItem());
        });

    }

    public void checkInternetConnection(boolean rs) {
        int choice = -1;
        Object[] options = {"Try Again", "Quit"};
        if (!rs) {choice = JOptionPane.showOptionDialog(this,"No internet connection","Error",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);}
        switch (choice) {
            case 0:
                while (!rs) {
                    checkInternetConnection(new ClassController().checkInternetConnection("www.google.com"));
                }
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

    public static void fieldGetApiKey(){
        JFrame panel = new JFrame();
        JTextField tf = new JTextField();
        JLabel lb = new JLabel("Enter your API key");
        JButton okButton = new JButton("Enter");

        panel.add(lb,BorderLayout.NORTH);
        panel.add(tf,BorderLayout.CENTER);
        panel.add(okButton,BorderLayout.SOUTH);
        panel.setSize(400,300);
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setVisible(true);

        okButton.addActionListener(e ->{
            if (tf.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid input !");
            }else{
                JOptionPane.showMessageDialog(null, "ok !");
                classController.setApi_key(tf.getText().trim());;
                panel.setVisible(false);
            }

        });

    }


}