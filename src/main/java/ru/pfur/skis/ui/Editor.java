package ru.pfur.skis.ui;

import ru.pfur.skis.command.AddBarCommand;
import ru.pfur.skis.command.AddNodeCommand;
import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
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
    private JScrollPane jScrollPane1;
    private JPopupMenu.Separator jSeparator1;
    private JSplitPane jSplitPane1;
    private JSplitPane jSplitPane3;
    private JTabbedPane jTabbedBottom;
    private JTabbedPane jTabbedContent;
    private JTabbedPane jTabbedProject;
    private JToolBar jToolBar1;
    public static Editor instance = null;
    private Model model = null;

    private Editor() {
        initComponents();
    }

    public Editor(Model model) {
        this.model = model;
        initComponents();

    }

    //
    private void initComponents() {
//
        jPanelContent = new JPanel();
        jPanelToolbar = new JPanel();
        jTabbedContent = new JTabbedPane();
        jToolBar1 = new JToolBar();
        jSplitPane1 = new JSplitPane();
        jTabbedBottom = new JTabbedPane();
        jPanel1 = new JPanel();
        jSplitPane3 = new JSplitPane();
        jTabbedProject = new JTabbedPane();
        jScrollPane1 = new JScrollPane();
//        jTree1 = new JTree();
        jMenuBar2 = new JMenuBar();
        jMenu1 = new JMenu();
        jSeparator1 = new JPopupMenu.Separator();
        jMenu2 = new JMenu();

        Panel3D fxPanel = new Panel3D(model);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Molecule Sample Application");
        setLocationByPlatform(true);
        setPreferredSize(new Dimension(1280, 720));

        jPanelContent.setLayout(new BorderLayout(4, 4));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new Dimension(13, 30));
        jToolBar1.setMinimumSize(new Dimension(13, 30));
        jToolBar1.setPreferredSize(new Dimension(13, 30));


        GroupLayout jPanelToolbarLayout = new GroupLayout(jPanelToolbar);
        jPanelToolbar.setLayout(jPanelToolbarLayout);
        jPanelToolbarLayout.setHorizontalGroup(
                jPanelToolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jToolBar1, GroupLayout.DEFAULT_SIZE, 966, Short.MAX_VALUE)
                        .addComponent(jTabbedContent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelToolbarLayout.setVerticalGroup(
                jPanelToolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelToolbarLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jTabbedContent, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jToolBar1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        jPanelContent.add(jPanelToolbar, BorderLayout.PAGE_START);

//        jSplitPane1.setDividerLocation(400);
        jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1.0);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedBottom.addTab("Console", jPanel1);
        jSplitPane1.setBottomComponent(jTabbedBottom);

        jSplitPane3.setDividerLocation(300);
        JPanel panel = new JPanel();
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

        panel.add(node);
        panel.add(bar);
        panel.add(beam);
        panel.add(truss);
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(model.getNodes().size());
//                new AddNodeCommand(model, new Node(-60,-60, -60));
//                System.out.println(model.getNodes().size());
//                fxPanel.reload();
//            }
//        });
        jScrollPane1.setViewportView(panel);

        jTabbedProject.addTab("Projects", jScrollPane1);
        jSplitPane3.setTopComponent(jTabbedProject);
        jSplitPane3.setRightComponent(fxPanel);
        jSplitPane1.setTopComponent(jSplitPane3);


        jPanelContent.add(jSplitPane1, BorderLayout.CENTER);
        getContentPane().add(jPanelContent, BorderLayout.CENTER);

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setJMenuBar(jMenuBar2);
        pack();
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
        Node n1 = new Node(10,10,10);
        Node n2 = new Node(30,30,30);
        Node n3 = new Node(60,60,60);
        Node n4 = new Node(30,60,60);
        Node n5 = new Node(60,30,60);
        new AddNodeCommand(model, n1);
        new AddNodeCommand(model, n2);
        new AddNodeCommand(model, n3);
        new AddNodeCommand(model, n4);
        new AddNodeCommand(model, n5);

        new AddBarCommand(model, new Bar(n1,n2));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
