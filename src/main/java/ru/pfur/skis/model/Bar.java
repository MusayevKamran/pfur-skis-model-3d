package ru.pfur.skis.model;

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
    public Stiffness stiffness = null;


    public Bar(Node nodeStart, Node nodeEnd) {
        this.nodeStart = nodeStart;
        this.nodeEnd = nodeEnd;
    }

    public void removeStiffness() {
        stiffness = null;
    }

    public Stiffness getStiffness() {
        return stiffness;
    }

    public void setStiffness(Stiffness stiffness) {
        this.stiffness = stiffness;
    }
}
