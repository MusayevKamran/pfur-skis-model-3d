package ru.pfur.skis.ui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.model.Selecteble;
import ru.pfur.skis.observer.AddElementSubscriber;
import ru.pfur.skis.observer.ChangeElementSubscriber;
import ru.pfur.skis.observer.RemoveElementSubscriber;
import ru.pfur.skis.ui.primitiv.BarBox;
import ru.pfur.skis.ui.primitiv.NodeBox;
import ru.pfur.test.Xform;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Kamran on 4/24/2016.
 */
public class Panel3D extends JPanel implements AddElementSubscriber, ChangeElementSubscriber, RemoveElementSubscriber {

    private static final double CAMERA_INITIAL_DISTANCE = -450;
    private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private static final double AXIS_LENGTH = 10000;
    private static final double CONTROL_MULTIPLIER = 0.1;
    private static final double SHIFT_MULTIPLIER = 10.0;
    private static final double MOUSE_SPEED = 0.1;
    private static final double ROTATION_SPEED = 2.0;
    private static final double TRACK_SPEED = 0.3;

    final PhongMaterial nodeSelectedColor = new PhongMaterial(Color.BLUE);
    final PhongMaterial nodeUnSelectedColor = new PhongMaterial(Color.RED);

    final PhongMaterial barSelectedColor = new PhongMaterial(Color.GREENYELLOW);
    final PhongMaterial barUnSelectedColor = new PhongMaterial(Color.LIGHTGRAY);

    NodeProperties menu = null;

    Group rootGroup = new Group();
    Xform modelGroup = new Xform();
    Xform axisGroup = new Xform();
    Xform world = new Xform();
    PerspectiveCamera camera = new PerspectiveCamera(true);
    Xform cameraXform = new Xform();
    Xform cameraXform2 = new Xform();
    Xform cameraXform3 = new Xform();
    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;
    Scene scene = null;
    private JFXPanel jfp = new JFXPanel();
    private Model model;

    public Panel3D(Model model, Dimension size) {
        this.model = model;
        menu = new NodeProperties(model);
        add(jfp, BorderLayout.CENTER);
        Platform.runLater(() -> {
            initGUI();
            buildModel();
            scene = new Scene(rootGroup, size.getWidth() - 10, size.height - 10, true, SceneAntialiasing.BALANCED);
            handleKeyboard(scene, world);
            handleMouse(scene, world);
            scene.setCamera(camera);
            jfp.setScene(scene);
        });

        model.subscribeAddElement(this);
        model.subscribeChangeElement(this);
        model.subscribeRemoveElement(this);
    }

    public void rotateAnimation() {
        double modifier = 1.0;
        Thread n = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX * MOUSE_SPEED * modifier * ROTATION_SPEED + 0.1);
//                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY * MOUSE_SPEED * modifier * ROTATION_SPEED);

                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });
        n.start();


    }

    private void handleMouse(Scene scene, final Node root) {
        scene.setOnScroll(se -> {
            root.setScaleX(root.getScaleX() + se.getDeltaY() * 0.001);
            root.setScaleY(root.getScaleY() + se.getDeltaY() * 0.001);
            root.setScaleZ(root.getScaleZ() + se.getDeltaY() * 0.001);
        });

        scene.setOnMousePressed(me -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();

            PickResult p = me.getPickResult();
            if (p.getIntersectedNode() != null) {
                Node n = p.getIntersectedNode();
                if (me.isPrimaryButtonDown()) {
                    changeColor(n);
                }
                if (me.isSecondaryButtonDown()) {
                    changeColor(n);


                    menu.show(null, (int) me.getScreenX(), (int) me.getScreenY());
                }
            }
        });

        scene.setOnMouseDragged(me -> {
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            mouseDeltaX = (mousePosX - mouseOldX);
            mouseDeltaY = (mousePosY - mouseOldY);

            double modifier = 1.0;

            if (me.isControlDown()) {
                modifier = CONTROL_MULTIPLIER;
            }
            if (me.isShiftDown()) {
                modifier = SHIFT_MULTIPLIER;
            }
            if (me.isPrimaryButtonDown()) {
                cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX * MOUSE_SPEED * modifier * ROTATION_SPEED);
                cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY * MOUSE_SPEED * modifier * ROTATION_SPEED);
            } else if (me.isSecondaryButtonDown()) {
                double z = camera.getTranslateZ();
                double newZ = z + mouseDeltaX * MOUSE_SPEED * modifier;
                camera.setTranslateZ(newZ);
            } else if (me.isMiddleButtonDown()) {
                cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX * MOUSE_SPEED * modifier * TRACK_SPEED);
                cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY * MOUSE_SPEED * modifier * TRACK_SPEED);
            }
        });
    }

    private void changeColor(Node node) {
        if (node instanceof Selecteble) {
            ((Selecteble) node).selected();
        }
    }

    private void handleKeyboard(Scene scene, final Node root) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case Z:
                    cameraXform2.t.setX(0.0);
                    cameraXform2.t.setY(0.0);
                    camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
                    cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
                    cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
                    break;
                case X:
                    axisGroup.setVisible(!axisGroup.isVisible());
                    break;
                case V:

                    break;
                case LEFT:
                    if (event.isShiftDown())
                        camera.setTranslateZ(camera.getTranslateZ() + 5);
                    else
                        camera.setTranslateX(camera.getTranslateX() + 5);
                    break;
                case RIGHT:
                    if (event.isShiftDown())
                        camera.setTranslateZ(camera.getTranslateZ() - 5);
                    else
                        camera.setTranslateX(camera.getTranslateX() - 5);
                    break;
                case UP:
                    camera.setTranslateY(camera.getTranslateY() - 5);
                    break;
                case DOWN:
                    camera.setTranslateY(camera.getTranslateY() + 5);
                    break;
            }
        });
    }

    public void initGUI() {
        buildCamera();
        buildAxes();
        createWorld();
        rootGroup.getChildren().add(world);
        rootGroup.setDepthTest(DepthTest.ENABLE);
    }

    private void buildCamera() {
        rootGroup.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
    }

    private void buildAxes() {
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final javafx.scene.shape.Box xAxis = new javafx.scene.shape.Box(AXIS_LENGTH, 0.1, 0.1);
        final javafx.scene.shape.Box yAxis = new javafx.scene.shape.Box(0.1, AXIS_LENGTH, 0.1);
        final javafx.scene.shape.Box zAxis = new javafx.scene.shape.Box(0.1, 0.1, AXIS_LENGTH);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        axisGroup.setVisible(true);

    }

    private void buildModel() {
        ArrayList<ru.pfur.skis.model.Node> nodes = (ArrayList<ru.pfur.skis.model.Node>) model.getNodes();
        nodes.forEach(this::createNode);
        ArrayList<ru.pfur.skis.model.Bar> bars = (ArrayList<ru.pfur.skis.model.Bar>) model.getBars();
        bars.forEach(this::createBar);
    }

    private void createBar(Bar bar) {
        ru.pfur.skis.model.Node n1 = bar.nodeStart;
        ru.pfur.skis.model.Node n2 = bar.nodeEnd;
        Point3D p1 = new Point3D(n1.x, n1.y, n1.z);
        Point3D p2 = new Point3D(n2.x, n2.y, n2.z);
        modelGroup.getChildren().add(createConnection(p1, p2, bar));
    }

    private void createNode(ru.pfur.skis.model.Node node) {
        NodeBox t1 = new NodeBox(1, 1, 1, node);
        t1.setTranslateX(node.getX());
        t1.setTranslateY(node.getY());
        t1.setTranslateZ(node.getZ());
        t1.setMaterial(nodeUnSelectedColor);
        modelGroup.getChildren().add(t1);
        jfp.repaint();

    }

    public Shape3D createConnection(Point3D origin, Point3D target, Bar bar) {
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D diff = target.subtract(origin);
        double height = diff.magnitude();
        Point3D mid = target.midpoint(origin);
        Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());
        Point3D axisOfRotation = diff.crossProduct(yAxis);
        double angle = Math.acos(diff.normalize().dotProduct(yAxis));
        Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);
        Shape3D line = new BarBox(.5, height, .5, bar);
        line.setMaterial(barUnSelectedColor);
        line.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);
        return line;
    }

    private Node createWorld() {
        world = new Xform();
        world.getChildren().addAll(axisGroup);
        world.getChildren().addAll(modelGroup);
        return world;
    }

    @Override
    public void addNode(Model model, ru.pfur.skis.model.Node node) {
        Platform.runLater(() -> createNode(node));
    }

    @Override
    public void addBar(Model model, Bar bar) {
        Platform.runLater(() -> createBar(bar));
    }

    @Override
    public void nodeSelectedChanged(Model model, ru.pfur.skis.model.Node node) {
        Material material;

        ObservableList<Node> children = modelGroup.getChildren();

        NodeBox n = children.parallelStream().filter(p -> p instanceof NodeBox).map(p -> (NodeBox) p).filter(p -> p.getNode().equals(node)).findFirst().get();
        if (n.getNode().isSelected())
            material = nodeSelectedColor;
        else material = nodeUnSelectedColor;

        Platform.runLater(() -> n.setMaterial(material));
    }

    @Override
    public void barSelectedChanged(Model model, Bar bar) {
        Material material;

        ObservableList<Node> children = modelGroup.getChildren();

        BarBox n = children.parallelStream().filter(p -> p instanceof BarBox).map(p -> (BarBox) p).filter(p -> p.getBar().equals(bar)).findFirst().get();
        if (n.getBar().isSelected())
            material = barSelectedColor;
        else material = barUnSelectedColor;

        Platform.runLater(() -> n.setMaterial(material));
    }

    @Override
    public void removeNode(Model model, ru.pfur.skis.model.Node node) {
        ObservableList<Node> children = modelGroup.getChildren();
        NodeBox n = children.parallelStream().filter(p -> p instanceof NodeBox).map(p -> (NodeBox) p).filter(p -> p.getNode().equals(node)).findFirst().get();

        Platform.runLater(() ->
                modelGroup.getChildren().remove(n));

    }

    @Override
    public void removeBar(Model model, Bar bar) {

    }
}
