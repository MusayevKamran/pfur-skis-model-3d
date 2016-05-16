package ru.pfur.skis.observer;

import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

/**
 * Created by Art on 15.05.16.
 */
public interface ChangeElementSubscriber {

    void nodeSelectedChanged(Model model, Node node);

    void barSelectedChanged(Model model, Bar bar);

}
