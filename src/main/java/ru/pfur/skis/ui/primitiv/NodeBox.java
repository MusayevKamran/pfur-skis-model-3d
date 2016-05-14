package ru.pfur.skis.ui.primitiv;

import javafx.scene.shape.Box;
import ru.pfur.skis.model.Node;

/**
 * Created by Kamran on 5/14/2016.
 */
public class NodeBox extends Box {

    private Node node;

    public NodeBox(double width, double height, double depth, Node node) {
        super(width, height, depth);
        this.node = node;
    }

    public Node getNode() {
        return node;
    }
}
