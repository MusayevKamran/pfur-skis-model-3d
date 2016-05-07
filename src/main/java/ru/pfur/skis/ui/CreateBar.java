package ru.pfur.skis.ui;

import javax.swing.*;

/**
 * Created by Kamran on 5/7/2016.
 */
public class CreateBar extends JFrame{

    public static void main(String[] args) {
    JFrame frame = new JFrame("Bar");
    JPanel panel = new JPanel();
    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    frame.setSize(300,150);
    frame.setVisible(true);

    JTextArea getX = new JTextArea();
    JTextArea getY = new JTextArea();
    JTextArea getZ = new JTextArea();

    panel.add(getX);
    panel.add(getY);
    panel.add(getZ);

    frame.add(panel);
}

}
