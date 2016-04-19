package ru.pfur.test;

import ru.pfur.skis.command.AddNodeCommand;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

/**
 * Created by Kamran on 3/26/2016.
 */
public class Runner {

    public static void main(String[] args) {
        Model model = new Model();
        TestView test1 = new TestView("Kamran View");
        TestView test2 = new TestView("Artem View");

        model.subscribeAddElement(test1);

        model.subscribeAddElement(test2);

        model.subscribeRemoveElement(test1);

        Node node = new Node(1, 1, 1);
        new AddNodeCommand(model, node);


//        System.out.println(model.getNodes().size());
//        new UndoCommand(model);
//      System.out.println(model.getNodes().size());
//        new RedoCommand(model);
////      System.out.println(model.getNodes().size());
//        Node node1 = new Node(1, 1, 1);
//        new AddNodeCommand(model, node1);
//
//        Node nod2e = new Node(1, 1, 1);
//        new AddNodeCommand(model, nod2e);

    }

}
