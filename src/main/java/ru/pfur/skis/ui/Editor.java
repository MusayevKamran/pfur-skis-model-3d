package ru.pfur.skis.ui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class Editor extends JFrame {
    private JButton jButton1;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuBar jMenuBar2;
    private JPanel jPanel1;
    private JPanel jPanelContent;
    private JPanel jPanelToolbar;
    private JScrollPane jScrollPane1;
    private JPopupMenu.Separator jSeparator1;
    private JToolBar.Separator jSeparator2;
    private JSplitPane jSplitPane1;
    private JSplitPane jSplitPane3;
    private JTabbedPane jTabbedBottom;
    private JTabbedPane jTabbedContent;
    private JTabbedPane jTabbedProject;
    private JToolBar jToolBar1;
    private JTree jTree1;
    public static Editor instance = null;

    private Editor() {
        initComponents();
    }
//
    private void initComponents() {
        jButton1 = new JButton();
        jPanelContent = new JPanel();
        jPanelToolbar = new JPanel();
        jTabbedContent = new JTabbedPane();
        jToolBar1 = new JToolBar();
        jSeparator2 = new JToolBar.Separator();
        jSplitPane1 = new JSplitPane();
        jTabbedBottom = new JTabbedPane();
        jPanel1 = new JPanel();
        jSplitPane3 = new JSplitPane();
        jTabbedProject = new JTabbedPane();
        jScrollPane1 = new JScrollPane();
        jTree1 = new JTree();
        jMenuBar2 = new JMenuBar();
        jMenu1 = new JMenu();
        jSeparator1 = new JPopupMenu.Separator();
        jMenu2 = new JMenu();
        Panel3D fxPanel = new Panel3D();


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Molecule Sample Application");
        setLocationByPlatform(true);
        setPreferredSize(new Dimension(1280, 720));

        jPanelContent.setLayout(new BorderLayout(4, 4));
        jPanelToolbar.setMaximumSize(new Dimension(32767, 30));
        jPanelToolbar.setMinimumSize(new Dimension(0, 30));
        jPanelToolbar.setPreferredSize(new Dimension(966, 30));

        jTabbedContent.setMaximumSize(new Dimension(32767, 30));
        jTabbedContent.setMinimumSize(new Dimension(105, 30));
        jTabbedContent.setPreferredSize(new Dimension(5, 30));

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

        DefaultMutableTreeNode treeProject = new DefaultMutableTreeNode("Projects");

        DefaultMutableTreeNode treeCreate = new DefaultMutableTreeNode("Create");
        DefaultMutableTreeNode treeDelete = new DefaultMutableTreeNode("Delete");

        DefaultMutableTreeNode createNode = new DefaultMutableTreeNode("Node");
        DefaultMutableTreeNode createBar = new DefaultMutableTreeNode("Bar");
        DefaultMutableTreeNode addSupport = new DefaultMutableTreeNode("Support");
        treeCreate.add(createNode);
        treeCreate.add(createBar);
        treeCreate.add(addSupport);
        treeProject.add(treeCreate);


        DefaultMutableTreeNode deleteNode = new DefaultMutableTreeNode("Node");
        DefaultMutableTreeNode deleteBar = new DefaultMutableTreeNode("Bar");
        DefaultMutableTreeNode deleteSupport = new DefaultMutableTreeNode("Support");
        treeDelete.add(deleteNode);
        treeDelete.add(deleteBar);
        treeDelete.add(deleteSupport);
        treeProject.add(treeDelete);


        jTree1.setModel(new DefaultTreeModel(treeProject));
        jScrollPane1.setViewportView(jTree1);

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
            instance = new Editor();
            instance.setLocationRelativeTo(null);
            instance.setVisible(true);
        });
    }

}
