package ru.pfur.skis.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kamran on 5/7/2016.
 */
public class CreateBar extends JFrame{

    JPanel panelForX;
    JPanel panelForY;
    JPanel panelForZ;
    JPanel panelForButtons;

    private JButton buttonAdd;
    private JButton buttonApply;
    private JButton buttonClose;

    private JLabel labelX1;
    private JLabel labelX2;
    private JLabel labelY1;
    private JLabel labelY2;
    private JLabel labelZ1;
    private JLabel labelZ2;

    private JTextField textFieldGetX1;
    private JTextField textFieldGetX2;
    private JTextField textFieldGetY1;
    private JTextField textFieldGetY2;
    private JTextField textFieldGetZ1;
    private JTextField textFieldGetZ2;

    public CreateBar(String s) throws HeadlessException {
        super(s);
        setTitle("Bar");
        setVisible(true);
        setAlwaysOnTop(true);
        setLocation(5, 115);
        setSize(new Dimension(500, 230));
        setLayout(new FlowLayout());

        panelForX = new JPanel();
        labelX1 = new JLabel("X1  =");
        labelX1.setPreferredSize(new Dimension(30, 30));
        panelForX.add(labelX1);
        textFieldGetX1 = new JTextField("15");
        textFieldGetX1.setPreferredSize(new Dimension(170, 30));
        panelForX.add(textFieldGetX1);

        labelX2 = new JLabel("X2  =");
        labelX2.setPreferredSize(new Dimension(30, 30));
        panelForX.add(labelX2);
        textFieldGetX2 = new JTextField("15");
        textFieldGetX2.setPreferredSize(new Dimension(170, 30));
        panelForX.add(textFieldGetX2);


        panelForY = new JPanel();
        labelY1 = new JLabel("Y1  = ");
        labelY1.setPreferredSize(new Dimension(30, 30));
        panelForY.add(labelY1);
        textFieldGetY1 = new JTextField("15");
        textFieldGetY1.setPreferredSize(new Dimension(170, 30));
        panelForY.add(textFieldGetY1);

        labelY2 = new JLabel("Y2  = ");
        labelY2.setPreferredSize(new Dimension(30, 30));
        panelForY.add(labelY2);
        textFieldGetY2 = new JTextField("15");
        textFieldGetY2.setPreferredSize(new Dimension(170, 30));
        panelForY.add(textFieldGetY2);


        panelForZ = new JPanel();
        labelZ1 = new JLabel("Z1  =");
        labelZ1.setPreferredSize(new Dimension(30, 30));
        panelForZ.add(labelZ1);
        textFieldGetZ1 = new JTextField("20");
        textFieldGetZ1.setPreferredSize(new Dimension(170, 30));
        panelForZ.add(textFieldGetZ1);

        labelZ2 = new JLabel("Z2  =");
        labelZ2.setPreferredSize(new Dimension(30, 30));
        panelForZ.add(labelZ2);
        textFieldGetZ2 = new JTextField("20");
        textFieldGetZ2.setPreferredSize(new Dimension(170, 30));
        panelForZ.add(textFieldGetZ2);

        panelForButtons = new JPanel();
        buttonAdd = new JButton("ADD");
        buttonAdd.setPreferredSize(new Dimension(80, 30));
        buttonApply = new JButton("APPLY");
        buttonApply.setPreferredSize(new Dimension(80, 30));
        buttonClose = new JButton("CLOSE");
        buttonClose.setPreferredSize(new Dimension(80, 30));

        panelForButtons.add(buttonAdd);
        panelForButtons.add(buttonApply);
        panelForButtons.add(buttonClose);

        this.add(panelForX);
        this.add(panelForY);
        this.add(panelForZ);
        this.add(panelForButtons);

    }

    public static void main(String[] args) {
        CreateBar frame = new CreateBar("Bar");
        frame.setVisible(true);
    }
}