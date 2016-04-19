package ru.pfur.skis.model;

/**
 * Created by Kamran on 3/19/2016.
 */
public class Node {
    public int x;
    public int y;
    public int z;
    public boolean selected;
    public Load load = null;
    public Support support = null;


    public Node(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Node{" +
                "y=" + y +
                ", x=" + x +
                ", z=" + z +
                ", selected=" + selected +
                ", load=" + load +
                ", support=" + support +
                '}';
    }

    public void removeLoad() {
        load = null;
    }

    public void removeSupport() {
        support = null;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Load getLoad() {
        return load;
    }

    public void setLoad(Load load) {
        this.load = load;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }


}
