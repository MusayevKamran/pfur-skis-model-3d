package ru.pfur.test;/**
 * Created by Art on 10.03.16.
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;


public class MainApplication extends Application {

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
    final Group rootGroup = new Group();
    final Xform testGroup = new Xform();
    final Xform axisGroup = new Xform();
    final Xform moleculeGroup = new Xform();
    final Xform world = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    //   private void buildScene() {
    //       rootGroup.getChildren().add(world);
    //   }
    private void buildCamera() {
        System.out.println("buildCamera()");
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
        System.out.println("buildAxes()");
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(AXIS_LENGTH, 0.5, 0.5);
        final Box yAxis = new Box(0.5, AXIS_LENGTH, 0.5);
        final Box zAxis = new Box(0.5, 0.5, AXIS_LENGTH);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        axisGroup.setVisible(true);
        world.getChildren().addAll(axisGroup);
    }

    private void handleMouse(Scene scene, final Node root) {
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
        ((Shape3D) n).setMaterial(greyMaterial);

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


    @Override
    public void start(Stage primaryStage) {

        // setUserAgentStylesheet(STYLESHEET_MODENA);
        System.out.println("start()");

        rootGroup.getChildren().add(world);
        rootGroup.setDepthTest(DepthTest.ENABLE);


        // buildScene();
        buildCamera();
        buildAxes();
        buildTest();


        Scene scene = new Scene(rootGroup, 1024, 768, true);
        scene.setFill(Color.GREY);

        handleKeyboard(scene, world);
        handleMouse(scene, world);
        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setCamera(camera);

    }

    public void createBar(Xform world, double x1, double y1, double z1, double x2, double y2, double z2) {
//
//        Box t1 = new Box(1, 1, 1);
//        t1.setTranslateX(x1);
//        t1.setTranslateY(y1);
//        t1.setTranslateZ(z1);
//        testGroup.getChildren().add(t1);
//
//        Box t2 = new Box(1, 1, 1);
//        t2.setTranslateX(x2);
//        t2.setTranslateY(y2);
//        t2.setTranslateZ(z2);
//        testGroup.getChildren().add(t2);
//
//        Point3D p1 = new Point3D(x1, y1, z1);
//        Point3D p2 = new Point3D(x2, y2, z2);
//        Box cc = (Box) createConnection(p1, p2);
//        world.getChildren().add(t1);
//        world.getChildren().add(t2);
//        world.getChildren().add(cc);
//        System.out.println(p2.angle(p1));
    }

    public void setCordinateIBeam(Xform world, double x1, double y1, double z1, double x2, double y2, double z2) {

        Box t1 = new Box(1, 1, 1);
        t1.setTranslateX(x1);
        t1.setTranslateY(y1);
        t1.setTranslateZ(z1);
        testGroup.getChildren().add(t1);

        Box t2 = new Box(1, 1, 1);
        t2.setTranslateX(x2);
        t2.setTranslateY(y2);
        t2.setTranslateZ(z2);
        testGroup.getChildren().add(t2);

        Point3D p1 = new Point3D(x1, y1, z1);
        Point3D p2 = new Point3D(x2, y2, z2);
        createConnectionIbeams(world, p1, p2);
        world.getChildren().add(t1);
        world.getChildren().add(t2);
        System.out.println(p2.angle(p1));
    }

    private void buildTest() {

        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        setCordinateIBeam(world, 0, 0, 0, 30, 0, 0);
        setCordinateIBeam(world, 0, 0, 0, 0, 30, 0);
        setCordinateIBeam(world, 0, 0, 0, 0, 0, 30);

        setCordinateIBeam(world, 30, 0, 30, 0, 0, 30);
        setCordinateIBeam(world, 30, 0, 30, 0, 0, 30);
        setCordinateIBeam(world, 30, 0, 30, 0, 0, 30);

        setCordinateIBeam(world, 30, 0, 30, 0, 30, 30);
        setCordinateIBeam(world, 30, 0, 30, 0, 30, 30);
        setCordinateIBeam(world, 30, 0, 30, 0, 30, 30);

        setCordinateIBeam(world, 30, 0, 30, 0, 30, 0);
        setCordinateIBeam(world, 30, 0, 30, 0, 30, 0);
        setCordinateIBeam(world, 30, 0, 30, 0, 30, 0);


        setCordinateIBeam(world, 30, 30, 0, 30, 0, 0);
        setCordinateIBeam(world, 30, 30, 0, 0, 30, 0);
        setCordinateIBeam(world, 30, 30, 0, 30, 30, 30);

        setCordinateIBeam(world, 0, 0, 0, 30, 0, 0);
        setCordinateIBeam(world, 0, 0, 0, 0, 30, 0);
        setCordinateIBeam(world, 0, 0, 0, 0, 0, 30);
        setCordinateIBeam(world, 30, 30, 30, 0, 30, 30);

//        setCordinateIBeam(world, 30, 0, 30, 0, 0, 30);
//        setCordinateIBeam(world, 30, 0, 0, 30, 30, 0);
//        setCordinateIBeam(world, 30, 15, 40, 12, 55, 43);
//        setCordinateIBeam(world, 0, 35, 29, 40, 33, 15);
//        setCordinateIBeam(world, 50, 5, 29, 62, 45, 33);

//        setCordinate(world, 0, 0, 0, 30, 30, 30);
//        setCordinate(world, 30, 30, 30, 30, 0, 30);
//        setCordinate(world, 30, 0, 30, 0, 0, 30);
//        setCordinate(world, 30, 0, 0, 30, 30, 0);


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

        Shape3D line = new Box(.5, height, .5);

        line.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);

        return line;
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

        Shape3D line1 = new Box(0.5, height, 2);
        line1.setTranslateX(1);
        Shape3D line2 = new Box(2, height, 0.5);
        Shape3D line3 = new Box(0.5, height, 2);
        line3.setTranslateX(-1);

        Xform iBeam = new Xform();
        iBeam.getChildren().add(line1);
        iBeam.getChildren().add(line2);
        iBeam.getChildren().add(line3);

//        System.out.println(-Math.toDegrees(angle));

        iBeam.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);
        world.getChildren().add(iBeam);

//        Rotate opositerotateAroundCenter = new Rotate(Math.toDegrees(angle), Rotate.Y_AXIS);
//        iBeam.getTransforms().add(opositerotateAroundCenter);
//        iBeam.getRotationAxis().angle(origin , target);

    }


}
