package ru.pfur.skis.observer;

import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

/**
 * Created by Kamran on 4/2/2016.
 */
public interface AddElementSubscriber {

    void addNode(Model model, Node node);

    void addBar(Model model, Bar bar);
}
