package ru.pfur.skis.model.model;

/**
 * Created by Kamran on 3/19/2016.
 */
public class Bar {
    public Node nodeStart;
    public Node nodeEnd;
    public boolean selected;
    public double area;
    public double eModulus;
    public double i;


    public Bar(Node nodeStart, Node nodeEnd) {
        this.nodeStart = nodeStart;
        this.nodeEnd = nodeEnd;
    }
}
