package br.com.yagodaoud.calc.gui;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {

    public Calculator(){

        arrangeLayout();

        setSize(232, 322);
        //setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void arrangeLayout() {
        setLayout(new BorderLayout());

        Display display = new Display();
        display.setPreferredSize(new Dimension(233, 60));
        add(display, BorderLayout.NORTH);

        Keyboard keyboard = new Keyboard();
        add(keyboard, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
