//
// Source code recreated from PriceComparator .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.pfur.skis.model;

import ru.pfur.skis.observer.AddElementSubscriber;
import ru.pfur.skis.observer.ChangeElementSubscriber;
import ru.pfur.skis.observer.RemoveElementSubscriber;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Set<Bar> removes;
        removes = bars.stream().filter(p -> p.nodeEnd.equals(node) || p.nodeStart.equals(node)).collect(Collectors.toSet());
        removes.forEach(this::removeBar);
        nodes.remove(node);
        notifyRemoveNode(node);
    }

    public void subscribeAddElement(AddElementSubscriber subscriber) {
        this.addSubscribers.add(subscriber);
    }

    public void subscribeRemoveElement(RemoveElementSubscriber subscriber) {
        this.removeSubscribers.add(subscriber);
    }

    public void subscribeChangeElement(ChangeElementSubscriber subscriber) {
        this.changeSubscribers.add(subscriber);
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

    public void nodeTranslateChanged(Node node) {
        changeSubscribers.forEach(p -> p.nodeTranslateChanged(this, node));
        findAndNotifyBars(node);
    }

    private void findAndNotifyBars(Node node) {

        bars.stream().filter(p -> p.nodeStart.equals(node) || p.nodeEnd.equals(node)).forEach(this::barNodeChanged);

    }

    public void barNodeChanged(Bar bar) {
        changeSubscribers.forEach(p -> p.barNodeChanged(this, bar));
    }


    public List<AddElementSubscriber> getAddSubscribers() {
        return addSubscribers;
    }

    public void setAddSubscribers(List<AddElementSubscriber> addSubscribers) {
        this.addSubscribers = addSubscribers;
    }

    public List<RemoveElementSubscriber> getRemoveSubscribers() {
        return removeSubscribers;
    }

    public void setRemoveSubscribers(List<RemoveElementSubscriber> removeSubscribers) {
        this.removeSubscribers = removeSubscribers;
    }

    public List<ChangeElementSubscriber> getChangeSubscribers() {
        return changeSubscribers;
    }

    public void setChangeSubscribers(List<ChangeElementSubscriber> changeSubscribers) {
        this.changeSubscribers = changeSubscribers;
    }
}
