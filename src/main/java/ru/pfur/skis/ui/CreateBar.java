package ru.pfur.skis.ui;

import ru.pfur.skis.Service;
import ru.pfur.skis.command.AddBarCommand;
import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;
import ru.pfur.skis.observer.ChangeElementSubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamran on 5/7/2016.
 */
public class CreateBar extends JFrame implements ChangeElementSubscriber {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 230;
    JPanel panelForX;
    JPanel panelForY;
    JPanel panelForZ;
    JPanel panelForButtons;
    int n = 0;
    Node n1;
    Node n2;
    private JButton buttonAdd;
    private JButton buttonApply;
    private JButton buttonClose;
    private JLabel labelX1;
    private JLabel labelX2;
    private JTextField textFieldGetX1;
    private JTextField textFieldGetX2;
    private Model model;

    public CreateBar(String s, Model model) throws HeadlessException {
        super(s);
        setTitle("Bar");
        setVisible(true);
        setAlwaysOnTop(true);
        Service.center(this, WIDTH, HEIGHT);
        setSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new FlowLayout());
        this.model = model;
        panelForX = new JPanel();
        labelX1 = new JLabel("Node 1  =");
        labelX1.setPreferredSize(new Dimension(30, 30));
        panelForX.add(labelX1);
        textFieldGetX1 = new JTextField("15");
        textFieldGetX1.setPreferredSize(new Dimension(170, 30));
        panelForX.add(textFieldGetX1);

        labelX2 = new JLabel("Node 2  =");
        labelX2.setPreferredSize(new Dimension(30, 30));
        panelForX.add(labelX2);
        textFieldGetX2 = new JTextField("15");
        textFieldGetX2.setPreferredSize(new Dimension(170, 30));
        panelForX.add(textFieldGetX2);

        panelForY = new JPanel();
        panelForZ = new JPanel();

        panelForButtons = new JPanel();
        buttonAdd = new JButton("ADD");
        buttonAdd.setPreferredSize(new Dimension(80, 30));

        buttonApply = new JButton("APPLY");
        buttonApply.setPreferredSize(new Dimension(80, 30));
        buttonApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBarCommand(model, new Bar(n1, n2));
                n1.selected();
                n2.selected();
            }
        });
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
        CreateBar frame = new CreateBar("Bar", new Model());
        frame.setVisible(true);
    }

    @Override
    public void nodeSelectedChanged(Model model, Node node) {
        if (n == 0) {
            textFieldGetX1.setText(node.getName());
            n1 = node;
            n++;
        } else {
            textFieldGetX2.setText(node.getName());
            n2 = node;
            n = 0;
        }
    }

    @Override
    public void barSelectedChanged(Model model, Bar bar) {

    }
}