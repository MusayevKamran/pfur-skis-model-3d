//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.pfur.skis.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model {
    private List<Node> nodes = new ArrayList();
    private List<Bar> bars = new ArrayList();

    public Model() {
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void addBar(Bar bar) {
        this.bars.add(bar);
    }

    public void removeBar(Bar bar) {
        this.bars.remove(bar);
    }

    public void removeNode(Node node) {
        ArrayList tmp = new ArrayList();
        Iterator var3 = this.bars.iterator();

        while (true) {
            Bar bar;
            do {
                if (!var3.hasNext()) {
                    this.bars.removeAll(tmp);
                    this.nodes.remove(node);
                    return;
                }

                bar = (Bar) var3.next();
            } while (!node.equals(bar.nodeEnd) && !node.equals(bar.nodeStart));

            tmp.add(bar);
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Bar> getBars() {
        return bars;
    }

    public void setBars(List<Bar> bars) {
        this.bars = bars;
    }
}
