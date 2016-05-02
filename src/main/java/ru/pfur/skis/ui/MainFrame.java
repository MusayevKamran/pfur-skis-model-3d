package ru.pfur.skis.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kamran on 4/24/2016.
 */
public class MainFrame extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {

        System.out.println("Created Panel3D "+
                SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("JavaFX/Swing 3D app");

        f.getContentPane().setLayout(new BorderLayout());
        Panel3D fxPanel = new Panel3D();
        f.getContentPane().add(fxPanel, BorderLayout.CENTER);
        f.setExtendedState(f.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);

    }
}
