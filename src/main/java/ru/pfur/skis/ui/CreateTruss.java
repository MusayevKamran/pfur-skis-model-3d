package ru.pfur.skis.ui;

import ru.pfur.skis.Service;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.scheme.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Kamran on 5/8/2016.
 */
public class CreateTruss  extends JFrame{

    public static final int WIDTH = 470;
    public static final int HEIGHT = 520;


    public CreateTruss(String s, Model model) throws HeadlessException {
        super(s);
        setTitle("Truss");
        setVisible(true);
        setAlwaysOnTop(true);
        Service.center(this, WIDTH, HEIGHT);
        setSize(new Dimension(WIDTH, HEIGHT));

        createButtons(model);
    }

    public static void main(String[] args) {
        CreateTruss frame = new CreateTruss("Truss", new Model());
        frame.setVisible(true);
    }

    private void createButtons(final Model model) {
        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton spherePictureButton = new JButton();
        spherePictureButton.setPreferredSize(new Dimension(90, 90));
        buttonPanel.add(spherePictureButton);
        ImageIcon icon = new ImageIcon(getClass().getResource("test.png"));
        icon = new ImageIcon(icon.getImage().getScaledInstance(100, 94, BufferedImage.SCALE_SMOOTH));
        spherePictureButton.setIcon(icon);
        JButton sphereButton = new JButton("Sphere");
        sphereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TestScheme(model);
            }
        });

        sphereButton.setPreferredSize(new Dimension(170, 90));
        buttonPanel.add(sphereButton);
        JButton sphereButtonAnimation = new JButton("Animation");
        sphereButtonAnimation.setPreferredSize(new Dimension(170, 90));
        buttonPanel.add(sphereButtonAnimation);

        JButton corrugatedSpherePicture = new JButton();
        corrugatedSpherePicture.setPreferredSize(new Dimension(90, 90));
        buttonPanel.add(corrugatedSpherePicture);
        ImageIcon corrugatedSphereIcon = new ImageIcon(getClass().getResource("test2.png"));
        corrugatedSphereIcon = new ImageIcon(corrugatedSphereIcon.getImage().getScaledInstance(100, 94, BufferedImage.SCALE_SMOOTH));
        corrugatedSpherePicture.setIcon(corrugatedSphereIcon);
        JButton corrugatedSphereButton = new JButton("Corrugated Sphere");
        corrugatedSphereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CorrugatedSphere(model);
            }
        });

        corrugatedSphereButton.setPreferredSize(new Dimension(170, 90));
        buttonPanel.add(corrugatedSphereButton);
        JButton corrugatedSphereAnimation = new JButton("Animation");
        corrugatedSphereAnimation.setPreferredSize(new Dimension(170, 90));
        buttonPanel.add(corrugatedSphereAnimation);


        JButton ellipsoidPicture = new JButton();
        ellipsoidPicture.setPreferredSize(new Dimension(90, 90));
        buttonPanel.add(ellipsoidPicture);
        ImageIcon ellipsoidIcon = new ImageIcon(getClass().getResource("test3.png"));
        ellipsoidIcon = new ImageIcon(ellipsoidIcon.getImage().getScaledInstance(100, 94, BufferedImage.SCALE_SMOOTH));
        ellipsoidPicture.setIcon(ellipsoidIcon);
        JButton ellipsoidButton = new JButton("Ellipsoid");
        ellipsoidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Ellipsoid(model);
            }
        });

        ellipsoidButton.setPreferredSize(new Dimension(170, 90));
        buttonPanel.add(ellipsoidButton);
        JButton ellipsoidAnimtarion = new JButton("Animation");
        ellipsoidAnimtarion.setPreferredSize(new Dimension(170, 90));
        buttonPanel.add(ellipsoidAnimtarion);

        JButton semiCon = new JButton();
        semiCon.setPreferredSize(new Dimension(90, 90));
        buttonPanel.add(semiCon);
        ImageIcon semiConIcon = new ImageIcon(getClass().getResource("test4.png"));
        semiConIcon = new ImageIcon(semiConIcon.getImage().getScaledInstance(100, 94, BufferedImage.SCALE_SMOOTH));
        semiCon.setIcon(semiConIcon);
        JButton semiConButton = new JButton("Semi Con");
        semiConButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SemiCon(model);
            }
        });

        semiConButton.setPreferredSize(new Dimension(170, 90));
        buttonPanel.add(semiConButton);
        JButton semiConAnimtarion = new JButton("Animation");
        semiConAnimtarion.setPreferredSize(new Dimension(170, 90));
        buttonPanel.add(semiConAnimtarion);

        JButton semiCycloid = new JButton();
        semiCycloid.setPreferredSize(new Dimension(90, 90));
        buttonPanel.add(semiCycloid);
        ImageIcon semiCycloidIcon = new ImageIcon(getClass().getResource("test5.png"));
        semiCycloidIcon = new ImageIcon(semiCycloidIcon.getImage().getScaledInstance(100, 94, BufferedImage.SCALE_SMOOTH));
        semiCycloid.setIcon(semiCycloidIcon);
        JButton semiCycloidButton = new JButton("Semi Cycloid");
        semiCycloidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SemiCycloid(model);
            }
        });

        semiCycloidButton.setPreferredSize(new Dimension(170, 90));
        buttonPanel.add(semiCycloidButton);
        JButton semiCycloidAnimation = new JButton("Animation");
        semiCycloidAnimation.setPreferredSize(new Dimension(170, 90));
        buttonPanel.add(semiCycloidAnimation);
    }
}
