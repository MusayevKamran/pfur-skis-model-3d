package ru.pfur.skis.model;

/**
 * Created by Kamran on 3/19/2016.
 */
public class Bar implements Selecteble {
    public Node nodeStart;
    public Node nodeEnd;
    public boolean selected;
    public double area;
    public double eModulus;
    public double i;
    public Stiffness stiffness = null;
    public Model model;
    public String name;

    public Integer index;


    public Bar(Node nodeStart, Node nodeEnd) {
        this.nodeStart = nodeStart;
        this.nodeEnd = nodeEnd;
        name = nodeStart.getName() + " : " + nodeEnd.getName();

    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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

    public Node getNodeStart() {
        return nodeStart;
    }

    public void setNodeStart(Node nodeStart) {
        this.nodeStart = nodeStart;
    }

    public Node getNodeEnd() {
        return nodeEnd;
    }

    public void setNodeEnd(Node nodeEnd) {
        this.nodeEnd = nodeEnd;
    }

    public String getName() {
        return name;
    }

    @Override
    public void selected() {
        this.selected = !this.selected;
        model.barSelectedChanged(this);
    }
}
