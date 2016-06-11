package ru.pfur.skis.command;

import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamran on 6/11/2016.
 */
public class CopyNodesCommand extends AbstractCommand implements Command {
    List<Node> copied;

    public CopyNodesCommand(Model model, List<Node> copyFrom) {
        super(model);
        this.copied = copy(copyFrom);
        manager.invokeCommand(this);

    }

    private List<Node> copy(List<Node> copyFrom) {
        List<Node> result = new ArrayList<>(copyFrom.size());
        for (Node node : copyFrom) {
            Node newNode = new Node(node.getX(), node.getY(), node.getZ());
            newNode.setSelected(true);
            node.setSelected(false);
            result.add(newNode);
        }
        return result;
    }

    @Override
    public boolean execute() {
        try {
            copied.forEach(node -> model.addNode(node));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean unExecute() {
        try {
            copied.forEach(node -> model.removeNode(node));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
