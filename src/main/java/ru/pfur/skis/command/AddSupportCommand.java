package ru.pfur.skis.command;

import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;
import ru.pfur.skis.model.Support;


public class AddSupportCommand extends AbstractCommand implements Command {
    private Node node = null;
    private Support support = null;


    public AddSupportCommand(Model model, Node node, Support support) {
        super(model);
        this.node = node;
        this.support = support;
    }

    @Override
    public boolean execute() {
        try {
            node.setSupport(support);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public boolean unExecute() {
        try {
            node.removeLoad();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

