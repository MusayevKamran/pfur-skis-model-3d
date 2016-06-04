package ru.pfur.skis.ui;

import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;
import ru.pfur.skis.observer.ChangeElementSubscriber;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


/**
 * Created by Kamran on 5/28/2016.
 */
public class NodeProperties extends JPopupMenu implements FocusListener, ChangeElementSubscriber {
    private JMenuItem infoItem;
    private JMenuItem properties;
    private JMenuItem removeItem;
    private JMenuItem closeItem;
    private Model model;
    private Node node;

    public NodeProperties(Model model) {
        super();
        this.model = model;
        model.subscribeChangeElement(this);
        infoItem = new JMenuItem();
        infoItem.setEnabled(false);

        properties = new JMenuItem("Properties");
        removeItem = new JMenuItem("Remove");
        closeItem = new JMenuItem("Close");

        properties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateNode t = new CreateNode("Node", node);
                t.setVisible(true);
                hideit();
            }
        });
        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.removeNode(node);
                hideit();
            }
        });
        closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideit();
            }
        });
        add(infoItem);
        addSeparator();
        add(properties);
        addSeparator();
        add(removeItem);
        addSeparator();
        add(closeItem);


    }


    @Override
    public void hide() {

//        this.removeWindowFocusListener( this );
    }

    private void hideit() {
        this.setVisible(false);
    }


    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        hideit();
    }

    @Override
    public void nodeSelectedChanged(Model model, Node node) {
        this.node = node;
        infoItem.setText(node.getName());
    }

    @Override
    public void barSelectedChanged(Model model, Bar bar) {

    }

    @Override
    public void nodeTranslateChanged(Model model, Node node) {

    }

    @Override
    public void barNodeChanged(Model model, Bar bar) {

    }
}
