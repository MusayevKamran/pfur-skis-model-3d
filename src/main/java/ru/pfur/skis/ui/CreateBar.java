package ru.pfur.skis.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kamran on 5/7/2016.
 */
public class CreateBar extends JFrame{

    private JButton ADD;
    private JButton APPLY;
    private JButton CLOSE;

    private JLabel labelX1;
    private JLabel labelX2;
    private JLabel labelY1;
    private JLabel labelY2;
    private JLabel labelZ1;
    private JLabel labelZ2;

    private JTextField getX1 = new JTextField();
    private JTextField getX2 = new JTextField();
    private JTextField getY1 = new JTextField();
    private JTextField getY2 = new JTextField();
    private JTextField getZ1 = new JTextField();
    private JTextField getZ2 = new JTextField();

    public CreateBar(String s) throws HeadlessException {
        super(s);
        setTitle("Bar");
        setVisible(true);
        setAlwaysOnTop(true);
        setSize(new Dimension(300, 310));
        setLayout(new FlowLayout());

        labelX1 = new JLabel("X 1");
        labelX1.setPreferredSize(new Dimension(100, 30));
        this.add(labelX1);
        getX1.setPreferredSize(new Dimension(100, 30));
        this.add(getX1);

        labelX2 = new JLabel("X 1");
        labelX2.setPreferredSize(new Dimension(100, 30));
        this.add(labelX2);
        getX2.setPreferredSize(new Dimension(100, 30));
        this.add(getX2);

        labelY1 = new JLabel("Y 1");
        labelY1.setPreferredSize(new Dimension(100, 30));
        this.add(labelY1);
        getY1.setPreferredSize(new Dimension(100, 30));
        this.add(getY1);

        labelY2 = new JLabel("Y 2");
        labelY2.setPreferredSize(new Dimension(100, 30));
        this.add(labelY2);
        getY2.setPreferredSize(new Dimension(100, 30));
        this.add(getY2);

        labelZ1 = new JLabel("Z 1");
        labelZ1.setPreferredSize(new Dimension(100, 30));
        this.add(labelZ1);
        getZ1.setPreferredSize(new Dimension(100, 30));
        this.add(getZ1);

        labelZ2 = new JLabel("Z 2");
        labelZ2.setPreferredSize(new Dimension(100, 30));
        this.add(labelZ2);
        getZ2.setPreferredSize(new Dimension(100, 30));
        this.add(getZ2);


        ADD = new JButton("  ADD  ");
        APPLY = new JButton("APPLY");
        CLOSE = new JButton("CLOSE");

        this.add(ADD);
        this.add(APPLY);
        this.add(CLOSE);

    }

    public static void main(String[] args) {
        CreateBar frame = new CreateBar("Bar");
        frame.setVisible(true);
    }
}