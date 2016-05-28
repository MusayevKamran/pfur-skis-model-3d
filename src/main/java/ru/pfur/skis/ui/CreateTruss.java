package ru.pfur.skis.ui;

import ru.pfur.skis.Service;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.scheme.TestScheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Kamran on 5/8/2016.
 */
public class CreateTruss  extends JFrame{

    public static final int WIDTH = 300;
    public static final int HEIGHT = 250;

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


    public CreateTruss(String s, Model model) throws HeadlessException {
        super(s);
        setTitle("Truss");
        setVisible(true);
        setAlwaysOnTop(true);
        Service.center(this, WIDTH, HEIGHT);
        setSize(new Dimension(WIDTH, HEIGHT));

        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton button1 = new JButton("Test");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TestScheme(model);
            }
        });

        ImageIcon icon = new ImageIcon(getClass().getResource("test.png"));
        icon = new ImageIcon(icon.getImage().getScaledInstance(100, 94, BufferedImage.SCALE_SMOOTH));
        button1.setIcon(icon);

        button1.setPreferredSize(new Dimension(270, 100));
        buttonPanel.add(button1);

        JButton button2 = new JButton("Test2");
        button2.setPreferredSize(new Dimension(270, 100));
        buttonPanel.add(button2);

    }

    public static void main(String[] args) {
        CreateTruss frame = new CreateTruss("Truss", new Model());
        frame.setVisible(true);
    }
}
