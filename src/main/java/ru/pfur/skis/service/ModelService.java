package ru.pfur.skis.service;

import com.google.gson.Gson;
import ru.pfur.skis.command.AddNodeCommand;
import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Node;
import ru.pfur.skis.observer.AddElementSubscriber;
import ru.pfur.skis.observer.ChangeElementSubscriber;
import ru.pfur.skis.observer.ModelSubscriber;
import ru.pfur.skis.observer.RemoveElementSubscriber;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Art on 04.06.16.
 */
public class ModelService {

    Set<ModelSubscriber> modelSubscribers = new HashSet<>();
    Gson gson = new Gson();
    private Model model;

    public ModelService(Model model) {
        this.model = model;
    }

    public Model create() {

        List<AddElementSubscriber> addSubscribers = model.getAddSubscribers();
        List<RemoveElementSubscriber> removeSubscribers = model.getRemoveSubscribers();
        List<ChangeElementSubscriber> changeSubscribers = model.getChangeSubscribers();

        this.model = new Model();
        notifyNewModel(model);

        model.setAddSubscribers(addSubscribers);
        model.setRemoveSubscribers(removeSubscribers);
        model.setChangeSubscribers(changeSubscribers);
        return this.model;
    }

    private void notifyNewModel(Model model) {
        modelSubscribers.forEach(p -> p.modelCreated(model));
    }

    public Model load(String path) {
        return new Model();
    }

    public Model save(String path) {
        return this.model;
    }

    public void subscribe(ModelSubscriber subscriber) {
        this.modelSubscribers.add(subscriber);
    }

    public void save(File file) throws Exception {
        try {
            String f = gson.toJson(translateModel());
            PrintWriter out = new PrintWriter(file);
            out.print(f);
            out.flush();
            out.close();
        } catch (Exception e) {
            throw e;
        }

    }

    private ModelDTO translateModel() {

        ModelDTO modelDTO = new ModelDTO();

        for (Bar bar : model.getBars()) {
            NodeDTO n1 = new NodeDTO();
            n1.setName(bar.getNodeStart().getName());
            n1.setIndex(bar.getNodeStart().getIndex());
            n1.setX(bar.getNodeStart().getX());
            n1.setY(bar.getNodeStart().getY());
            n1.setZ(bar.getNodeStart().getZ());

            modelDTO.getNodes().add(n1);

            NodeDTO n2 = new NodeDTO();
            n2.setName(bar.getNodeEnd().getName());
            n2.setIndex(bar.getNodeEnd().getIndex());
            n2.setX(bar.getNodeEnd().getX());
            n2.setY(bar.getNodeEnd().getY());
            n2.setZ(bar.getNodeEnd().getZ());

            modelDTO.getNodes().add(n2);

            BarDTO barDTO = new BarDTO();
            barDTO.setStartNode(n1);
            barDTO.setEndNode(n2);
            modelDTO.getBars().add(barDTO);
        }

        return modelDTO;

    }

    public void load(File file) throws Exception {

        Map<String, Node> nodeMap = new HashMap<>();

        Scanner in = new Scanner(new FileReader(file));
        String f = in.next();
        ModelDTO modelDTO = gson.fromJson(f, ModelDTO.class);

        create();

        modelDTO.getNodes().forEach(p -> nodeMap.put(p.getName(), new Node(p.getX(), p.getY(), p.getZ(), p.getName())));
        Collection<Node> nodes = nodeMap.values();
        for (BarDTO barDTO : modelDTO.bars) {
            Node n1 = nodeMap.get(barDTO.getStartNode().getName());
            Node n2 = nodeMap.get(barDTO.getEndNode().getName());

            Bar b1 = new Bar(n1, n2);
            model.addNode(n1);
            model.addNode(n2);
            model.addBar(b1);

            nodes.remove(n1);
            nodes.remove(n2);

        }

        for (Node node : nodes) {
            new AddNodeCommand(model, node);
        }

    }


//
//    private Node getNode(Map<String, Node> nodeMap, NodeDTO startNode) {
//
//        Node t = nodeMap.get(startNode.getName());
//        if (t == null) {
//
//            nodeMap.put(startNode.getName(), n);
//            return n;
//        }
//        return t;
//
//    }
}
