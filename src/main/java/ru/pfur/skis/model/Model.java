//
// Source code recreated from PriceComparator .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.pfur.skis.model;

import ru.pfur.skis.observer.AddElementSubscriber;
import ru.pfur.skis.observer.ChangeElementSubscriber;
import ru.pfur.skis.observer.RemoveElementSubscriber;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Model {
    private List<Node> nodes = new ArrayList();
    private List<Bar> bars = new ArrayList();

    private List<AddElementSubscriber> addSubscribers = new LinkedList<>();
    private List<RemoveElementSubscriber> removeSubscribers = new LinkedList<>();
    private List<ChangeElementSubscriber> changeSubscribers = new LinkedList<>();

    public Model() {
    }

    public void addNode(Node node) {
        this.nodes.add(node);
        node.setModel(this);
        notifyAddNode(node);
    }

    public void addBar(Bar bar) {
        this.bars.add(bar);
        bar.setModel(this);
        notifyAddBar(bar);
    }

    public void removeBar(Bar bar) {
        notifyRemoveBar(bar);
        this.bars.remove(bar);

    }

    public void removeNode(Node node) {
        notifyRemoveNode(node);

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

    public void subscribeAddElement(AddElementSubscriber subscriber) {
        this.addSubscribers.add(subscriber);
    }

    public void subscribeChangeElement(ChangeElementSubscriber subscriber) {
        this.changeSubscribers.add(subscriber);
    }

    public void subscribeRemoveElement(RemoveElementSubscriber subscriber) {
        this.removeSubscribers.add(subscriber);
    }

    public void notifyAddNode(Node node) {
        for (AddElementSubscriber elem : addSubscribers) {
            elem.addNode(this, node);
        }
    }

    public void notifyRemoveNode(Node node) {
        for (RemoveElementSubscriber elem : removeSubscribers) {
            elem.removeNode(this, node);
        }
    }

    public void notifyAddBar(Bar bar) {
        for (AddElementSubscriber elem : addSubscribers) {
            elem.addBar(this, bar);
        }
    }

    public void notifyRemoveBar(Bar bar) {
        for (RemoveElementSubscriber elem : removeSubscribers) {
            elem.removeBar(this, bar);
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

    public void nodeSelectedChanged(Node node) {
        changeSubscribers.forEach(p -> p.nodeSelectedChanged(this, node));
    }

    public void barSelectedChanged(Bar bar) {
        changeSubscribers.forEach(p -> p.barSelectedChanged(this, bar));
    }
}
