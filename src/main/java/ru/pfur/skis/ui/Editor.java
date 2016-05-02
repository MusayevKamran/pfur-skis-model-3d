package ru.pfur.skis.ui;

import javax.swing.*;

public class Editor extends JFrame {
    public static Editor instance = null;

    private Editor() {
        initComponents();
    }

    private void initComponents() {

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

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("J3D Build");
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(1280, 720));

//        jPanelContent.setBackground(ThemeManager.COLOR_BACKGROUND);
        jPanelContent.setLayout(new java.awt.BorderLayout(4, 4));

//        jPanelToolbar.setBackground(ThemeManager.COLOR_BACKGROUND);
        jPanelToolbar.setMaximumSize(new java.awt.Dimension(32767, 58));
        jPanelToolbar.setMinimumSize(new java.awt.Dimension(0, 58));
        jPanelToolbar.setPreferredSize(new java.awt.Dimension(966, 58));

        jTabbedContent.setMaximumSize(new java.awt.Dimension(32767, 30));
        jTabbedContent.setMinimumSize(new java.awt.Dimension(105, 30));
        jTabbedContent.setPreferredSize(new java.awt.Dimension(5, 30));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(13, 28));
        jToolBar1.setMinimumSize(new java.awt.Dimension(13, 28));
        jToolBar1.setPreferredSize(new java.awt.Dimension(13, 28));


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
                                .addComponent(jTabbedContent, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jToolBar1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        jPanelContent.add(jPanelToolbar, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setDividerLocation(400);
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

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Projects");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Test Project");
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(jTree1);

        jTabbedProject.addTab("Projects", jScrollPane1);

        jSplitPane3.setTopComponent(jTabbedProject);


        Panel3D fxPanel = new Panel3D();
        jSplitPane3.setRightComponent(fxPanel);

        jSplitPane1.setTopComponent(jSplitPane3);

        jPanelContent.add(jSplitPane1, java.awt.BorderLayout.CENTER);
        getContentPane().add(jPanelContent, java.awt.BorderLayout.CENTER);

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
}
