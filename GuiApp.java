/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TemperatureConverterGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuiApp implements ActionListener {
    JFrame frame;
    Container c;
    JButton convert, clear;
    JLabel l1, l2, l3, l4;
    JTextField tf1, tf2;
    JComboBox<String> cb1, cb2;
    JPanel p;
    JButton[] btn = new JButton[12];

    public GuiApp() {
        frame = new JFrame("Temp Converter");
        frame.setBounds(300, 100, 310, 480);
        c = frame.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(200, 230, 255));

        l1 = new JLabel("Temperature");
        l1.setBounds(10, 10, 100, 20);
        tf1 = new JTextField();
        tf1.setBounds(10, 30, 120, 30);
        tf1.setBackground(Color.yellow);

        l2 = new JLabel("Converted Value");
        l2.setBounds(10, 70, 120, 20);
        tf2 = new JTextField();
        tf2.setBounds(10, 90, 120, 30);
        tf2.setEditable(false);
        tf2.setBackground(Color.green);

        
        l3 = new JLabel("From");
        l3.setBounds(160, 10, 100, 20);
        l4 = new JLabel("To");
        l4.setBounds(160, 70, 100, 20);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        cb1 = new JComboBox<>(units);
        cb1.setBounds(160, 30, 120, 30);
        cb2 = new JComboBox<>(units);
        cb2.setBounds(160, 90, 120, 30);

        convert = new JButton("Convert");
        convert.setBounds(10, 140, 120, 30);
        convert.addActionListener(this);

        clear = new JButton("Clear");
        clear.setBounds(160, 140, 120, 30);
        clear.addActionListener(this);

        p = new JPanel();
        p.setBounds(10, 180, 270, 200);
        p.setLayout(new GridLayout(3, 4, 5, 5));
        p.setBackground(new Color(220, 240, 255));

        for (int i = 0; i < btn.length; i++) {
            btn[i] = new JButton();
            btn[i].addActionListener(this);
            if (i <= 8) btn[i].setText(Integer.toString(i + 1));
            p.add(btn[i]);
        }

        btn[9].setText("0");
        btn[10].setText(".");
        btn[11].setText("+-");

        c.add(l1);
        c.add(tf1);
        c.add(l2);
        c.add(tf2);
        c.add(l3);
        c.add(cb1);
        c.add(l4);
        c.add(cb2);
        c.add(convert);
        c.add(clear);
        c.add(p);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i <= 9; i++) {
            if (e.getSource() == btn[i]) {
                tf1.setText(tf1.getText() + btn[i].getText());
                return;
            }
        }

        if (e.getSource() == btn[10]) {
            if (!tf1.getText().contains(".")) {
                tf1.setText(tf1.getText() + ".");
            }
            return;
        }

        if (e.getSource() == btn[11]) {
            String text = tf1.getText();
            if (!text.isEmpty()) {
                if (text.startsWith("-")) {
                    tf1.setText(text.substring(1));
                } else {
                    tf1.setText("-" + text);
                }
            }
            return;
        }

        if (e.getSource() == convert) {
            try {
                double input = Double.parseDouble(tf1.getText());
                int from = cb1.getSelectedIndex();
                int to = cb2.getSelectedIndex();
                double result = convertTemperature(input, from, to);
                tf2.setText(String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid temperature input.");
            }
            return;
        }

        if (e.getSource() == clear) {
            tf1.setText("");
            tf2.setText("");
        }
    }

    private double convertTemperature(double value, int from, int to) {
        switch (from) {
            case 1: value = (value - 32) * 5 / 9; break; // Fahrenheit → Celsius
            case 2: value = value - 273.15; break;       // Kelvin → Celsius
        }

        switch (to) {
            case 1: return (value * 9 / 5) + 32; // Celsius → Fahrenheit
            case 2: return value + 273.15;       // Celsius → Kelvin
            default: return value;               // Celsius → Celsius
        }
    }
    public static void main(String[] args) {
    new GuiApp();
}
}