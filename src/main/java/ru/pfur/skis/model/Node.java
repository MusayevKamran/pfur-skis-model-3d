package ru.pfur.skis.model;

/**
 * Created by Kamran on 3/19/2016.
 */
public class Node {
    public int x;
    public int y;
    public int z;
    public boolean selected;
    public Load load = new Load();
    public Support support = new Support();

    public Node(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
