package ru.pfur.test;

import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;
import ru.pfur.skis.observer.AddElementSubscriber;
import ru.pfur.skis.observer.RemoveElementSubscriber;

/**
 * Created by Kamran on 4/2/2016.
 */
public class TestView implements AddElementSubscriber, RemoveElementSubscriber {

    private String name;

    public TestView(String name) {
        this.name = name;
    }

    @Override
    public void addNode(Model model, Node node) {
        System.out.println(name + "     " + node);
    }

    @Override
    public void addBar(Model model, Bar bar) {
        System.out.println(name + "     " + bar);
    }

    @Override
    public void removeNode(Model model, Node node) {
    }

    @Override
    public void removeBar(Model model, Bar bar) {

    }
}
