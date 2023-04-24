package br.com.yagodaoud.calc.gui;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    public Button(String text, Color color) {
        setText(text);
        setOpaque(true);
        setBackground(color);
        setFont(new Font("courier", Font.PLAIN, 30));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setForeground(Color.WHITE);
    }
}
