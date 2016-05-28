package ru.pfur.skis.model.scheme;

import ru.pfur.skis.model.Model;

/**
 * Created by Kamran on 5/28/2016.
 */
public interface Scheme {
    void generate(Model model);

    void action(Model model);
}
