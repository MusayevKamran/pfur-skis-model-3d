package ru.pfur.skis.command;

import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kamran on 6/11/2016.
 */
public class NodeGroupCopyAndTranslate extends AbstractCommand implements Command {

    Command copy;
    Command translate;
    List<Node> nodes;
    List<Node> selected;

    public NodeGroupCopyAndTranslate(Model model, List<Node> nodes) {
        super(model);
        this.nodes = nodes;
//        this.copy = copy;
//        this.translate = translate;
    }

    @Override
    public boolean execute() {
        copy = new CopyNodesCommand(model, nodes);
        this.selected = model.getNodes().stream().filter(node -> node.isSelected()).collect(Collectors.toList());
        translate = new TranslateNodesCommand(model, selected, null);
        return false;
    }

    @Override
    public boolean unExecute() {
        return false;
    }
}
