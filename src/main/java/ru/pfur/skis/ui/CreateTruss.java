package ru.pfur.skis.ui;

import ru.pfur.skis.model.Model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kamran on 5/8/2016.
 */
public class CreateTruss  extends JFrame{

    JPanel panelForX;
    JPanel panelForY;
    JPanel panelForZ;
    JPanel panelForButtons;

    private JLabel labelX;
    private JLabel labelY;
    private JLabel labelZ;
    private Model model;

    private JTextField textFieldGetX;
    private JTextField textFieldGetY;
    private JTextField textFieldGetZ;

    private JButton buttonAdd;
    private JButton buttonApply;
    private JButton buttonClose;


    public CreateTruss(String s) throws HeadlessException {
        super(s);
        setTitle("Truss");
        setVisible(true);
        setAlwaysOnTop(true);
        setLocation(5, 115);
        setSize(new Dimension(300, 230));
        setLayout(new FlowLayout());

        panelForX = new JPanel();
        labelX = new JLabel("X  =");
        labelX.setPreferredSize(new Dimension(25, 30));
        panelForX.add(labelX);
        textFieldGetX = new JTextField("15");
        textFieldGetX.setPreferredSize(new Dimension(170, 30));
        panelForX.add(textFieldGetX);

        panelForY = new JPanel();
        labelY = new JLabel("Y  = ");
        labelY.setPreferredSize(new Dimension(25, 30));
        panelForY.add(labelY);
        textFieldGetY = new JTextField("15");
        textFieldGetY.setPreferredSize(new Dimension(170, 30));
        panelForY.add(textFieldGetY);

        panelForZ = new JPanel();
        labelZ = new JLabel("Z  =");
        labelZ.setPreferredSize(new Dimension(25, 30));
        panelForZ.add(labelZ);
        textFieldGetZ = new JTextField("15");
        textFieldGetZ.setPreferredSize(new Dimension(170, 30));
        panelForZ.add(textFieldGetZ);


        panelForButtons = new JPanel();
        buttonAdd = new JButton("Add");
        buttonAdd.setPreferredSize(new Dimension(80, 30));

        buttonApply = new JButton("Apply");
        buttonApply.setPreferredSize(new Dimension(80, 30));

        buttonClose = new JButton("Close");
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
        CreateNode frame = new CreateNode("Node", new Model());
        frame.setVisible(true);
    }
}
