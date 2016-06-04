package ru.pfur.skis.observer;

import ru.pfur.skis.model.Model;

/**
 * Created by Art on 04.06.16.
 */
public interface ModelSubscriber {

    void modelCreated(Model model);

    void modelLoaded(Model model);

    void modelSaved(Model model);
}
