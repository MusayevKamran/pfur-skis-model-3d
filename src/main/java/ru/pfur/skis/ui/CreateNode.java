package ru.pfur.skis.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kamran on 5/7/2016.
 */
public class CreateNode extends JFrame {
    private JButton ADD;
    private JButton APPLY;
    private JButton CLOSE;
    private JLabel labelX;
    private JLabel labelY;
    private JLabel labelZ;

    private JTextField getX = new JTextField();
    private JTextField getY = new JTextField();
    private JTextField getZ = new JTextField();

    public CreateNode(String s) throws HeadlessException {
        super(s);
        setTitle("Node");
        setVisible(true);
        setAlwaysOnTop(true);
        setSize(new Dimension(300, 200));
        setLayout(new FlowLayout());
        labelX = new JLabel("X");
        labelX.setPreferredSize(new Dimension(100, 30));
        this.add(labelX);
        getX.setPreferredSize(new Dimension(100, 30));
        this.add(getX);
        labelY = new JLabel("Y");
        labelY.setPreferredSize(new Dimension(100, 30));
        this.add(labelY);
        getY.setPreferredSize(new Dimension(100, 30));
        this.add(getY);
        labelZ = new JLabel("Z");
        labelZ.setPreferredSize(new Dimension(100, 30));
        this.add(labelZ);
        getZ.setPreferredSize(new Dimension(100, 30));
        this.add(getZ);

        ADD = new JButton("  ADD  ");
        APPLY = new JButton("APPLY");
        CLOSE = new JButton("CLOSE");

        this.add(ADD);
        this.add(APPLY);
        this.add(CLOSE);

    }

    public static void main(String[] args) {
        CreateNode frame = new CreateNode("Node");
        frame.setVisible(true);
    }
}
