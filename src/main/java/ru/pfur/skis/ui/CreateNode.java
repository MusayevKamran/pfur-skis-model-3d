package ru.pfur.skis.ui;

import ru.pfur.skis.command.AddNodeCommand;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamran on 5/7/2016.
 */
public class CreateNode extends JFrame {
    private JButton buttonAdd;
    private JButton buttonApply;
    private JButton buttonClose;
    private JLabel labelX;
    private JLabel labelY;
    private JLabel labelZ;
    private Model model;

    private JTextField getX = new JTextField();
    private JTextField getY = new JTextField();
    private JTextField getZ = new JTextField();

    public CreateNode(String name, Model model) throws HeadlessException {
        super(name);
        this.model = model;
        init();
    }

    public void init() {
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
        this.setResizable(false);

        buttonAdd = new JButton("Add");
        buttonAdd.setPreferredSize(new Dimension(70, 30));
        buttonAdd.addActionListener(e -> {
            int x = Integer.parseInt(getX.getText());
            int y = Integer.parseInt(getY.getText());
            int z = Integer.parseInt(getZ.getText());

            new AddNodeCommand(model, new Node(x,y,z));

        });
        buttonApply = new JButton("Apply");
        buttonApply.setPreferredSize(new Dimension(70, 30));
        buttonClose = new JButton("Close");
        buttonClose.setPreferredSize(new Dimension(70, 30));

        this.add(buttonAdd);
        this.add(buttonApply);
        this.add(buttonClose);
    }

    public static void main(String[] args) {
        CreateNode frame = new CreateNode("Node", new Model());
        frame.setVisible(true);
    }
}
