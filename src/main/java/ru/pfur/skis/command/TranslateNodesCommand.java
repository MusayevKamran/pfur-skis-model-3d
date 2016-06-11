package ru.pfur.skis.command;

import javafx.geometry.Point3D;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;

import java.util.List;

/**
 * Created by Kamran on 6/11/2016.
 */
public class TranslateNodesCommand extends AbstractCommand implements Command {

    List<Node> nodes;
    Point3D point;

    public TranslateNodesCommand(Model model, List<Node> nodes, Point3D point) {
        super(model);
        this.nodes = nodes;
        this.point = point;
        manager.invokeCommand(this);
    }

    @Override
    public boolean execute() {
        try {
            nodes.forEach(n -> n.translate(n.getX() + (int) point.getX(), n.getY() + (int) point.getY(), n.getZ() + (int) point.getZ()));
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean unExecute() {
        try {
            nodes.forEach(n -> n.translate(n.getX() - (int) point.getX(), n.getY() - (int) point.getY(), n.getZ() - (int) point.getZ()));
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
