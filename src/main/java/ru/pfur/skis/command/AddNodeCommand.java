/**
 *
 */
package ru.pfur.skis.command;

import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

public class AddNodeCommand extends AbstractCommand implements Command {
    private Node node = null;

    public AddNodeCommand(Model model, Node node) {
        super(model);
        this.node = node;
        AbstractCommand.manager.invokeCommand(this);
    }

    @Override
    public boolean execute() {
        try {
            model.addNode(node);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean unExecute() {
        try {
            model.removeNode(node);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
