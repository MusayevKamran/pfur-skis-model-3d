package ru.pfur.skis.model;

import ru.pfur.skis.Service;

/**
 * Created by Kamran on 3/19/2016.
 */
public class Node implements Selecteble {
    public int x;
    public int y;
    public int z;
    public boolean selected;
    public Load load = null;
    public Support support = null;
    public Model model;

    public Integer index;
    public String name;

    public Node(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;

        index = Service.getNextIndex();
        name = "node" + index;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    @Override
    public void selected() {
        this.selected = !this.selected;
        model.nodeSelectedChanged(this);
    }

    public void translate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        model.nodeTranslateChanged(this);

    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", selected=" + selected +
                ", load=" + load +
                ", support=" + support +
                ", model=" + model +
                ", index=" + index +
                ", name='" + name + '\'' +
                '}';
    }
}
