package ru.pfur.skis.ui;

import ru.pfur.skis.Service;
import ru.pfur.skis.command.AddNodeCommand;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kamran on 5/7/2016.
 */
public class CreateNode extends JFrame {


    public static final int WIDTH = 300;
    public static final int HEIGHT = 230;

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

    public CreateNode(String name, Model model) throws HeadlessException {
        super(name);
        this.model = model;
        init();
    }

    public static void main(String[] args) {
        CreateNode frame = new CreateNode("Node", new Model());
        frame.setVisible(true);
    }

    public void init() {
        setTitle("Node");
        setVisible(true);
        setAlwaysOnTop(true);
        Service.center(this, WIDTH, HEIGHT);
        setSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new FlowLayout());
        setResizable(false);

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

        buttonAdd.addActionListener(e -> {
            int x = Integer.parseInt(textFieldGetX.getText());
            int y = Integer.parseInt(textFieldGetY.getText());
            int z = Integer.parseInt(textFieldGetZ.getText());

            new AddNodeCommand(model, new Node(x,y,z));

        });
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
}
