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

public class Editor extends JFrame implements ActionListener {
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuBar jMenuBar2;
    private JPanel jPanel1;
    private JPanel jPanelContent;
    private JPanel jPanelToolbar;
    private JScrollPane leftPane;
    private JSplitPane centerPane;
    private JSplitPane bottomPane;
    private JTabbedPane jTabbedBottom;
    private JTabbedPane jTabbedContent;
    private JTabbedPane jTabbedProject;
    private JToolBar jToolBar;
    private static Editor instance = null;
    private Model model = null;
    private Panel3D fxPanel = null;
    private GroupLayout jPanelToolbarLayout;

    private Editor() {
        initComponents();
    }

    public Editor(Model model) {
        this.model = model;
        initComponents();

    }

    private void initComponents() {
        setTitle("3D Model Design");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setPreferredSize(new Dimension(1280, 720));

        jPanelContent = new JPanel();
        jPanelToolbar = new JPanel();
        jTabbedContent = new JTabbedPane();
        jToolBar = new JToolBar();
        jTabbedBottom = new JTabbedPane();
        jPanel1 = new JPanel();
        leftPane = new JScrollPane();
        centerPane = new JSplitPane();
        bottomPane = new JSplitPane();
        jTabbedProject = new JTabbedPane();
        jMenuBar2 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenu2 = new JMenu();
        fxPanel = new Panel3D(model);
        fxPanel.setPreferredSize(new Dimension(1000,1000));
        jPanelToolbarLayout = new GroupLayout(jPanelToolbar);

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);
        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setJMenuBar(jMenuBar2);

        jPanelContent.setLayout(new BorderLayout(4, 4));
        jToolBar.setFloatable(false);
        jToolBar.setRollover(true);
        jToolBar.setMaximumSize(new Dimension(13, 30));
        jToolBar.setMinimumSize(new Dimension(13, 30));
        jToolBar.setPreferredSize(new Dimension(13, 30));


        jPanelToolbar.setLayout(jPanelToolbarLayout);
        jPanelToolbarLayout.setHorizontalGroup(
                jPanelToolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jToolBar, GroupLayout.DEFAULT_SIZE, 966, Short.MAX_VALUE)
                        .addComponent(jTabbedContent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelToolbarLayout.setVerticalGroup(
                jPanelToolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelToolbarLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jTabbedContent, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jToolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        jPanelContent.add(jPanelToolbar, BorderLayout.PAGE_START);


        //Left Panel
        jTabbedProject.addTab("Projects", leftPane);
        centerPane.setTopComponent(jTabbedProject);
        centerPane.setRightComponent(fxPanel);
        bottomPane.setTopComponent(centerPane);
        centerPane.setDividerLocation(290);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JButton node = new JButton("Node");
        node.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateNode node = new CreateNode("Node");
                node.setVisible(true);
            }
        });

        JButton bar = new JButton("Bar");
        bar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateBar bar = new CreateBar("Bar");
                bar.setVisible(true);
            }
        });

        JButton beam = new JButton("Beam");
        JButton truss = new JButton("Truss");
        truss.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(model.getNodes().size());
                new AddNodeCommand(model, new Node(-60,-60, -60));
                System.out.println(model.getNodes().size());

                UpdateModelBad();
            }
        });

        panel.add(node);
        panel.add(bar);
        panel.add(beam);
        panel.add(truss);
        leftPane.setViewportView(panel);


        jPanelContent.add(bottomPane, BorderLayout.CENTER);
        getContentPane().add(jPanelContent, BorderLayout.CENTER);

        //        bottomPane.setDividerLocation(400);
        jTabbedBottom.addTab("Console", jPanel1);
        bottomPane.setResizeWeight(1.0);
        bottomPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        bottomPane.setBottomComponent(jTabbedBottom);

        pack();
    }

    public void UpdateModelBad() {
        fxPanel = new Panel3D(model);
        fxPanel.setPreferredSize(new Dimension(1000,1000));

        centerPane.getComponentCount();
        centerPane.remove(2);
        centerPane.setRightComponent(fxPanel);
        centerPane.repaint(0,0,0,1000,1000);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            Model model = new Model();
            createTestModel(model);
            instance = new Editor(model);
            instance.setLocationRelativeTo(null);
            instance.setVisible(true);
        });
    }

    private static void createTestModel(Model model) {
        double t = 0;
        double s = 0;
        Node prev = null;
        int num = 0;
        double f = (Math.PI / 2 - (-Math.PI / 2)) / 200;

        for (t = (-Math.PI / 2); t < (Math.PI / 2); t = t + f) {

            for (s = 0; s < (Math.PI / 2); s = s + 0.05) {
                num ++;
                int x = (int) ((1 + Math.cos(2 * (90 * t))) * Math.cos(2 * (360 * s)) * 100) ;
                int y = (int) ((1 + Math.cos(2 * (90 * t))) * Math.sin(2 * (360 * s)) * 100) ;
                int z = (int) (Math.sin(2 * (90 * t)) * Math.sin(360 * s) * 100) ;
                Node n = new Node(x, y, z);
                new AddNodeCommand(model, n);

                if (prev != null && prev != n){
                    new AddBarCommand(model, new Bar(prev, n));
                    num++;
                }
                prev = n;
            }

        }
//        Node n1 = new Node(10, 10, 10);
//        Node n2 = new Node(30, 30, 30);
//        Node n3 = new Node(60, 60, 60);
//        Node n4 = new Node(30, 60, 60);
//        Node n5 = new Node(60, 30, 60);
//
//        new AddNodeCommand(model, n1);
//        new AddNodeCommand(model, n2);
//        new AddNodeCommand(model, n3);
//        new AddNodeCommand(model, n4);
//        new AddNodeCommand(model, n5);
//
//        new AddBarCommand(model, new Bar(n1, n2));

        System.out.println(num);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
