package ru.pfur.skis.command;

import ru.pfur.skis.model.Load;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;


public class AddLoadCommand extends AbstractCommand implements Command {
    private Node node = null;
    private Load load = null;


    public AddLoadCommand(Model model, Node node, Load load) {
        super(model);
        this.node = node;
        this.load = load;
    }

    @Override
    public boolean execute() {
        try {
            node.setLoad(load);
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

