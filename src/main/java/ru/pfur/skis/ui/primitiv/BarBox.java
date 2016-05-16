package ru.pfur.skis.ui.primitiv;

import javafx.scene.shape.Box;
import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Selecteble;

/**
 * Created by Art on 15.05.16.
 */
public class BarBox extends Box implements Selecteble {
    private Bar bar;

    public BarBox(double v, double v1, double v2, Bar bar) {
        super(v, v1, v2);
        this.bar = bar;
    }

    @Override
    public void selected() {
        bar.selected();
    }

    public Bar getBar() {
        return bar;
    }
}
