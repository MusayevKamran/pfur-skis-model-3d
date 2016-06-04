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
 * Created by Kamran on 6/4/2016.
 */
public class BarProperties extends JPopupMenu implements FocusListener, ChangeElementSubscriber {

    private JMenuItem infoItem;
    private JMenuItem removeItem;
    private JMenuItem closeItem;
    private Model model;
    private Bar bar;

    public BarProperties(Model model) {
        super();
        this.model = model;
        model.subscribeChangeElement(this);
        infoItem = new JMenuItem();
        infoItem.setEnabled(false);

        removeItem = new JMenuItem("Remove");
        closeItem = new JMenuItem("Close");

        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.removeBar(bar);
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
        add(removeItem);
        addSeparator();
        add(closeItem);
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void nodeSelectedChanged(Model model, Node node) {

    }

    @Override
    public void barSelectedChanged(Model model, Bar bar) {
        this.bar = bar;
        infoItem.setText(bar.getName());
    }

    @Override
    public void nodeTranslateChanged(Model model, Node node) {

    }

    @Override
    public void barNodeChanged(Model model, Bar bar) {

    }

    private void hideit() {
        this.setVisible(false);
    }
}
