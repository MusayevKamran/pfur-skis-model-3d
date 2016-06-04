package ru.pfur.skis.ui.primitiv;

import javafx.scene.shape.Box;
import ru.pfur.skis.ui.Translateble;

/**
 * Created by Kamran on 6/4/2016.
 */
public class TranslateBox extends Box implements Translateble {

    private double value;
    private int axis;

    public TranslateBox(double width, double height, double depth, double value, int axis) {
        super(width, height, depth);
        this.value = value;
        this.axis = axis;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public int axis() {
        return axis;
    }
}
