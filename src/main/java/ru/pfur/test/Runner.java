package ru.pfur.test;

import ru.pfur.skis.command.AddNodeCommand;
import ru.pfur.skis.command.RedoCommand;
import ru.pfur.skis.command.UndoCommand;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

/**
 * Created by Kamran on 3/26/2016.
 */
public class Runner {

    public static void main(String[] args) {
        Model model = new Model();
        System.out.println(model.getNodes().size());
        Node node = new Node(1, 1, 1);
        new AddNodeCommand(model, node);
        System.out.println(model.getNodes().size());
        new UndoCommand(model);
        System.out.println(model.getNodes().size());
        new RedoCommand(model);
        System.out.println(model.getNodes().size());

    }

}
