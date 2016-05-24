package ru.pfur.skis.ui;

import ru.pfur.skis.command.AddBarCommand;
import ru.pfur.skis.command.AddNodeCommand;
import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class FrameDesign extends JFrame {
    JFrame frame;
    JPanel panel;
    JMenuBar menuBar;
    JToolBar toolBar;
    JMenu file;
    JMenu about;
    JMenuItem openMenuItem;
    JMenuItem saveMenuItem;
    JMenuItem saveAsMenuItem;
    JMenuItem closeMenuItem;
    JMenuItem exitMenuItem;
    JMenuItem aboutMenuItem;
    JButton createNode;
    JButton createBar;
    JButton createTruss;

    FrameDesign(Model model) {
        setTitle("3D Design");
        setPreferredSize(new Dimension(1300, 720));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        file = new JMenu("File");
        about = new JMenu("About");

        menuBar.add(file);
        menuBar.add(about);
        setJMenuBar(menuBar);

        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        saveAsMenuItem = new JMenuItem("Save as ...");
        closeMenuItem = new JMenuItem("Close");
        exitMenuItem = new JMenuItem("Exit");
        aboutMenuItem = new JMenuItem("About");

        file.add(openMenuItem);
        file.add(saveMenuItem);
        file.add(saveAsMenuItem);
        file.add(closeMenuItem);
        file.add(exitMenuItem);
        about.add(aboutMenuItem);


        toolBar = new JToolBar();
        JButton copyButton = new JButton();
//        ImageIcon copyIcon = new ImageIcon(getClass().getResource("icon/new.gif"));
//        copyIcon = new ImageIcon(copyIcon.getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH));
//        copyButton.setText(null);
//        copyButton.setIcon(copyIcon);
        copyButton.setToolTipText("Copy");
        toolBar.add(copyButton);


        JButton pasteButton = new JButton();
        ImageIcon pasteIcon = new ImageIcon(getClass().getResource("open.gif"));
        pasteIcon = new ImageIcon(pasteIcon.getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH));
        pasteButton.setText("");
        pasteButton.setIcon(pasteIcon);
        pasteButton.setToolTipText("Paste");
        toolBar.add(pasteButton);
        getContentPane().add(toolBar, BorderLayout.NORTH);

        panel = new JPanel((new FlowLayout(FlowLayout.LEFT)));
        panel.setPreferredSize(new Dimension(30, 500));

        createNode = new JButton("Node");
        createNode.setPreferredSize(new Dimension(80, 30));
        createNode.setLocation(0, 0);
        createNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateNode("Node", model);
            }
        });

        createBar = new JButton("Bar");
        createBar.setPreferredSize(new Dimension(80, 30));
        createBar.setLocation(80, 30);
        createBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateBar("Bar");
            }
        });

        createTruss = new JButton("Truss");
        createTruss.setPreferredSize(new Dimension(80, 30));
        createTruss.setLocation(160, 30);
        createTruss.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateTruss("Truss");
            }
        });

        panel.add(createNode);
        panel.add(createBar);
        panel.add(createTruss);
        add(panel);

        Panel3D fxPanel = new Panel3D(model);
        panel.add(fxPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    private static void createTestModel(Model model) {
        double t = 0;
        double s = 0;
        Node prev = null;
        int num = 0;
        double f = (Math.PI / 2 - (-Math.PI / 2)) / 200;


        for (t = (-Math.PI / 2); t < (Math.PI / 2); t = t + f) {
            for (s = 0; s < (Math.PI / 2); s = s + 0.05) {

                num++;
                int x = (int) ((1 + Math.cos(2 * (Math.PI / 2 * t))) * Math.cos(2 * (2 * Math.PI * s)) * 100);
                int y = (int) ((1 + Math.cos(2 * (Math.PI / 2 * t))) * Math.sin(2 * (2 * Math.PI * s)) * 100);
                int z = (int) (Math.sin(2 * (Math.PI / 2 * t)) * Math.sin(2 * Math.PI * s) * 100);
                Node n = new Node(x, y, z);
                new AddNodeCommand(model, n);

                if (prev != null && prev != n) {
                    new AddBarCommand(model, new Bar(prev, n));
                    num++;
                }
                prev = n;
            }

        }


        System.out.println(num);
    }

    public static void main(String[] args) {
        Model model = new Model();
        new FrameDesign(model);

    }

}