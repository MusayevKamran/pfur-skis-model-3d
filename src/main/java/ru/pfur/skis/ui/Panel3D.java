package ru.pfur.skis.ui;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import ru.pfur.skis.model.Bar;
import ru.pfur.skis.model.Model;
import ru.pfur.skis.observer.AddElementSubscriber;
import ru.pfur.skis.ui.primitiv.NodeBox;
import ru.pfur.test.Xform;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Kamran on 4/24/2016.
 */
public class Panel3D extends JPanel implements AddElementSubscriber {

    private static final double CAMERA_INITIAL_DISTANCE = -450;
    private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private static final double AXIS_LENGTH = 2500.0;
    private static final double HYDROGEN_ANGLE = 104.5;
    private static final double CONTROL_MULTIPLIER = 0.1;
    private static final double SHIFT_MULTIPLIER = 10.0;
    private static final double MOUSE_SPEED = 0.1;
    private static final double ROTATION_SPEED = 2.0;
    private static final double TRACK_SPEED = 0.3;
    Group rootGroup = new Group();
    //    Xform modelGroup = new Xform();
    Xform modelGroup = new Xform();
    Xform axisGroup = new Xform();
    Xform moleculeGroup = new Xform();
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
    private int tttt = 0;
    private JFXPanel jfp = new JFXPanel();
    private Model model;

//    private StackPane root = new StackPane();

    public Panel3D(Model model) {
        initGUI();
        this.model = model;
        buildModel();
        scene = new Scene(rootGroup, 1345, 610, true, SceneAntialiasing.BALANCED);
        jfp.setScene(scene);
        add(jfp, BorderLayout.CENTER);
        handleKeyboard(scene, world);
        handleMouse(scene, world);
        scene.setCamera(camera);
        model.subscribeAddElement(this);
    }

    private void handleMouse(Scene scene, final Node root) {

        scene.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent se) {
                root.setScaleX(root.getScaleX() + se.getDeltaY() * 0.001);
                root.setScaleY(root.getScaleY() + se.getDeltaY() * 0.001);
                root.setScaleZ(root.getScaleZ() + se.getDeltaY() * 0.001);
            }
        });


        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();

                PickResult p = me.getPickResult();
                if (p.getIntersectedNode() != null) {
                    Node n = p.getIntersectedNode();
                    changeColor(n);
                }


            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
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
            }
        });
    }

    private void changeColor(Node n) {
        final PhongMaterial greyMaterial = new PhongMaterial();
        greyMaterial.setDiffuseColor(Color.BLUE);
        greyMaterial.setSpecularColor(Color.RED);
        if (n instanceof NodeBox) {
            NodeBox t = (NodeBox) n;
            t.setMaterial(greyMaterial);
            t.getNode().setSelected(true);
        }


    }

    private void handleKeyboard(Scene scene, final Node root) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            public void handle(KeyEvent event) {
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
                        moleculeGroup.setVisible(!moleculeGroup.isVisible());
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
//        System.out.println("buildAxes()");
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

        modelGroup.getChildren().add(createConnection(p1, p2));
    }

    private void createNode(ru.pfur.skis.model.Node node) {
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);
        NodeBox t1 = new NodeBox(1, 1, 1, node);
        t1.setTranslateX(node.getX());
        t1.setTranslateY(node.getY());
        t1.setTranslateZ(node.getZ());
        t1.setMaterial(redMaterial);
        modelGroup.getChildren().add(t1);
        jfp.repaint();

    }

    public Shape3D createConnection(Point3D origin, Point3D target) {
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D diff = target.subtract(origin);
        double height = diff.magnitude();

        Point3D mid = target.midpoint(origin);
        Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());

        Point3D axisOfRotation = diff.crossProduct(yAxis);
        double angle = Math.acos(diff.normalize().dotProduct(yAxis));
        Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);

        Shape3D line = new javafx.scene.shape.Box(.5, height, .5);

        line.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);

        return line;
    }


    public void setCordinateIBeam(Xform world, double x1, double y1, double z1, double x2, double y2, double z2) {

        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        javafx.scene.shape.Box t1 = new javafx.scene.shape.Box(1, 1, 1);
        t1.setTranslateX(x1);
        t1.setTranslateY(y1);
        t1.setTranslateZ(z1);
        t1.setMaterial(redMaterial);
        modelGroup.getChildren().add(t1);

        javafx.scene.shape.Box t2 = new javafx.scene.shape.Box(1, 1, 1);
        t2.setTranslateX(x2);
        t2.setTranslateY(y2);
        t2.setTranslateZ(z2);
        t2.setMaterial(redMaterial);
        modelGroup.getChildren().add(t2);

        Point3D p1 = new Point3D(x1, y1, z1);
        Point3D p2 = new Point3D(x2, y2, z2);
        createConnectionIbeams(world, p1, p2);
        world.getChildren().add(t1);
        world.getChildren().add(t2);
        System.out.println(p2.angle(p1));
    }

    public void createConnectionIbeams(Xform world, Point3D origin, Point3D target) {
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D diff = target.subtract(origin);
        double height = diff.magnitude();

        Point3D mid = target.midpoint(origin);
        Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());

        Point3D axisOfRotation = diff.crossProduct(yAxis);
        double angle = Math.acos(diff.normalize().dotProduct(yAxis));
        Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);

        Shape3D line1 = new javafx.scene.shape.Box(0.5, height, 2);
        line1.setTranslateX(1);
        Shape3D line2 = new javafx.scene.shape.Box(2, height, 0.5);
        Shape3D line3 = new javafx.scene.shape.Box(0.5, height, 2);
        line3.setTranslateX(-1);

        Xform iBeam = new Xform();
        iBeam.getChildren().add(line1);
        iBeam.getChildren().add(line2);
        iBeam.getChildren().add(line3);

//        System.out.println(-Math.toDegrees(angle));

        iBeam.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);
        world.getChildren().add(iBeam);


    }

    public void reload() {
        rootGroup = new Group();
        scene.setRoot(rootGroup);
        rootGroup.getChildren().add(createWorld());

    }

    private Node createWorld() {
        world = new Xform();
        world.getChildren().addAll(axisGroup);
        world.getChildren().addAll(modelGroup);
        return world;
    }

    @Override
    public void addNode(Model model, ru.pfur.skis.model.Node node) {

        if (tttt % 2 == 0) {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("NEW");
                    final PhongMaterial redMaterial = new PhongMaterial();
                    redMaterial.setDiffuseColor(Color.DARKRED);
                    redMaterial.setSpecularColor(Color.BLUE);
                    Sphere oxygenSphere = new Sphere(15.0);
                    oxygenSphere.setMaterial(redMaterial);
                    oxygenSphere.setTranslateX(150);
                    Xform n = new Xform();
                    n.getChildren().add(oxygenSphere);
                    modelGroup.getChildren().addAll(oxygenSphere);
                    System.out.println("NEW");
                }
            });

            tttt++;
        }
        else
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    Sphere oxygenSphere = (Sphere) modelGroup.getChildren().get(0);
                    oxygenSphere.translateXProperty().setValue(-150);
                    System.out.println("NEW");
                }
            });

            tttt++;
        }

    }

    @Override
    public void addBar(Model model, Bar bar) {

    }
}
