package br.com.yagodaoud.calc.gui;

import javax.swing.*;
import java.awt.*;

public class Keyboard extends JPanel {

    private final Color DARK_GREY = new Color(68, 68, 68);
    private final Color LIGHT_GREY = new Color(99, 99, 99);
    private final Color ORANGE = new Color(242, 163, 60);

    public Keyboard() {

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        setLayout(layout);

        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;

        //1st row
        addButton("AC", DARK_GREY, constraints, 0, 0);
        addButton("+/-", DARK_GREY, constraints, 1, 0);
        addButton("%", DARK_GREY, constraints, 2, 0);
        addButton("/", ORANGE, constraints, 3, 0);

        //2nd row
        addButton("7", LIGHT_GREY, constraints, 0, 1);
        addButton("8", LIGHT_GREY, constraints, 1, 1);
        addButton("9", LIGHT_GREY, constraints, 2, 1);
        addButton("*", ORANGE, constraints, 3, 1);

        //3rd row
        addButton("4", LIGHT_GREY, constraints, 0, 2);
        addButton("5", LIGHT_GREY, constraints, 1, 2);
        addButton("6", LIGHT_GREY, constraints, 2, 2);
        addButton("-", ORANGE, constraints, 3, 2);

        //4th row
        addButton("1", LIGHT_GREY, constraints, 0, 3);
        addButton("2", LIGHT_GREY, constraints, 1, 3);
        addButton("3", LIGHT_GREY, constraints, 2, 3);
        addButton("+", ORANGE, constraints, 3, 3);

        //5th row
        constraints.gridwidth = 2;
        addButton("0", LIGHT_GREY, constraints, 0, 4);
        constraints.gridwidth = 1;
        addButton(",", LIGHT_GREY, constraints, 2, 4);
        addButton("=", ORANGE, constraints, 3, 4);


    }

    private void addButton(String text, Color color, GridBagConstraints constraints, int x, int y){

        constraints.gridx = x;
        constraints.gridy = y;
        Button button = new Button(text, color);
        add(button, constraints);
    }
}