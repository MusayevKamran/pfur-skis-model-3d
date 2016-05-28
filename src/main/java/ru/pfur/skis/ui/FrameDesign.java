package ru.pfur.skis.ui;

import ru.pfur.skis.command.AddBarCommand;
import ru.pfur.skis.command.AddNodeCommand;
import ru.pfur.skis.command.RedoCommand;
import ru.pfur.skis.command.UndoCommand;
import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class FrameDesign extends JFrame {
    JPanel panelButton;
    Panel3D fxPanel;
    JPanel panel3D;
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
    private Model model = null;

    FrameDesign(Model model) {
        this.model = model;
        setTitle("3D Design");
        setPreferredSize(new Dimension(1500, 720));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createMenu();
        createToolBar(model);
        create3dPanel(model);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        Model model = new Model();
        new FrameDesign(model);
    }

    private void create3dPanel(Model model) {
        panel3D = new JPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fxPanel = new Panel3D(model, screenSize);
        panel3D.add(fxPanel, BorderLayout.CENTER);
        add(panel3D, BorderLayout.CENTER);
    }

    private void createToolBar(final Model model) {
        toolBar = new JToolBar();
        JButton newButton = createButton("new");
        getContentPane().add(toolBar, BorderLayout.NORTH);
        toolBar.add(newButton);


        JButton openButton = createButton("open");
        getContentPane().add(toolBar, BorderLayout.NORTH);
        toolBar.add(openButton);

        JButton saveButton = createButton("save");
        getContentPane().add(toolBar, BorderLayout.NORTH);
        toolBar.add(saveButton);

        toolBar.addSeparator();

        JButton back = createButton("back");
        getContentPane().add(toolBar, BorderLayout.NORTH);
        toolBar.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UndoCommand(model);
            }
        });

        JButton next = createButton("next");
        getContentPane().add(toolBar, BorderLayout.NORTH);
        toolBar.add(next);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RedoCommand(model);
            }
        });
        toolBar.addSeparator();

        JButton cutButton = createButton("cut");
        getContentPane().add(toolBar, BorderLayout.NORTH);
        toolBar.add(cutButton);

        JButton copyButton = createButton("copy");
        getContentPane().add(toolBar, BorderLayout.NORTH);
        toolBar.add(copyButton);


        JButton pasteButton = createButton("paste");
        getContentPane().add(toolBar, BorderLayout.NORTH);
        toolBar.add(pasteButton);


        toolBar.addSeparator();

        JButton action = createButton("action");
        getContentPane().add(toolBar, BorderLayout.NORTH);
        toolBar.add(action);
        action.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doAction();
            }
        });

        createNode = createButton("node");
        createNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateNode("Node", model);
            }
        });


        createBar = createButton("bar");
        createBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.subscribeChangeElement(new CreateBar("Bar", model));
            }
        });

        createTruss = createButton("truss");
        createTruss.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateTruss("Truss", model);
            }
        });

        toolBar.add(createNode);
        toolBar.add(createBar);
        toolBar.add(createTruss);
    }

    private void doAction() {
        fxPanel.rotateAnimation();
        Thread n = new Thread(new Runnable() {
            @Override
            public void run() {
//                for (int i = 0; i < 10; i++){
//                    for (int j = 0; j < 10; j++){
//                        for (int k = 0; k < 10; k++){
//                            new AddNodeCommand(model, new Node(i*3,j*3,k*3));
//                            try {
//                                Thread.currentThread().sleep(10);
//                            } catch (InterruptedException e1) {
//                                e1.printStackTrace();
//                            }
//                        }
//                    }
//                }

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
                        try {
                            Thread.currentThread().sleep(50);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        prev = n;
                    }
                }
            }
        });
        n.start();
    }

    private void createMenu() {
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
    }

    private JButton createButton(String type) {
        JButton button = new JButton();
        ImageIcon icon = new ImageIcon(getClass().getResource(type + ".png"));
        icon = new ImageIcon(icon.getImage().getScaledInstance(24, 24, BufferedImage.SCALE_SMOOTH));
        button.setText("");
        button.setIcon(icon);
        button.setToolTipText(Character.toUpperCase(type.charAt(0)) + type.substring(1));
        return button;
    }
}