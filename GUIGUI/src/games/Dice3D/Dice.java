package games.Dice3D;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Created by Daniel_Gonik on 05.08.15.
 */

public class Dice extends Application {

    @Override public void start(Stage stage) throws Exception {
        //main stage and title
        stage.setTitle("Dice 3D");

        //main dice, its size and angles
        Cube dice3D = new Cube(300);
        dice3D.rx.setAngle(45);
        dice3D.ry.setAngle(45);


        //here is our timeouts for animation
        Timeline animation = new Timeline();
        animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(dice3D.rx.angleProperty(), 0d)
                ),
                new KeyFrame(Duration.valueOf("5000ms"),
                        new KeyValue(dice3D.rx.angleProperty(), 360d)
                ),
                new KeyFrame(Duration.ZERO,
                        new KeyValue(dice3D.ry.angleProperty(), 0d)
                ),
                new KeyFrame(Duration.valueOf("5000ms"),
                        new KeyValue(dice3D.ry.angleProperty(), 180d)
                ),
                new KeyFrame(Duration.valueOf("10000ms"),
                        new KeyValue(dice3D.ry.angleProperty(), 540d)
                )
        );
        //infinite animation
        animation.setCycleCount(Animation.INDEFINITE);
        // create root group
        Group root = new Group(dice3D);
        // aligning the dice
        root.setTranslateX(1300 / 2);
        root.setTranslateY(800 / 2);
        // create scene, set it to fullscreen and adding to main stage
        // depthBuffer - true, balanced aliasing
        Scene scene = new Scene(root, 1200,850, true, SceneAntialiasing.BALANCED);
        stage.setFullScreen(true);
        scene.setCamera(new PerspectiveCamera());
        stage.setScene(scene);
        stage.show();
        // start spining animation
        animation.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

//    private static final String side1URL = "file:" + System.getProperty("user.dir") + "/src/games/Dice3D/img/png/border-radius/1.png";
//    private static final String side2URL = "file:" + System.getProperty("user.dir") + "/src/games/Dice3D/img/png/border-radius/2.png";
//    private static final String side3URL = "file:" + System.getProperty("user.dir") + "/src/games/Dice3D/img/png/border-radius/3.png";
//    private static final String side4URL = "file:" + System.getProperty("user.dir") + "/src/games/Dice3D/img/png/border-radius/4.png";
//    private static final String side5URL = "file:" + System.getProperty("user.dir") + "/src/games/Dice3D/img/png/border-radius/5.png";
//    private static final String side6URL = "file:" + System.getProperty("user.dir") + "/src/games/Dice3D/img/png/border-radius/6.png";

    public class Cube extends Group {
        //we need 3 axis for 3D effect
        final Rotate rx = new Rotate(0,Rotate.X_AXIS);
        final Rotate ry = new Rotate(0,Rotate.Y_AXIS);
        final Rotate rz = new Rotate(0,Rotate.Z_AXIS);
        public Cube(double size) {
            //sides of dice
            Image side1 = new Image(String.valueOf(getClass().getResource("img/png/border-radius/1.png")));
            Image side2 = new Image(String.valueOf(getClass().getResource("img/png/border-radius/2.png")));
            Image side3 = new Image(String.valueOf(getClass().getResource("img/png/border-radius/3.png")));
            Image side4 = new Image(String.valueOf(getClass().getResource("img/png/border-radius/4.png")));
            Image side5 = new Image(String.valueOf(getClass().getResource("img/png/border-radius/5.png")));
            Image side6 = new Image(String.valueOf(getClass().getResource("img/png/border-radius/6.png")));

            getTransforms().addAll(rz, ry, rx);
            getChildren().addAll(
                    RectangleBuilder.create() // 1
                            .width(size).height(size)
                            .fill(new ImagePattern(side1, 0, 0, size, size, false))
                            .translateX(-0.5 * size)
                            .translateY(-0.5 * size)
                            .translateZ(0.5 * size)
                            .build(),
                    RectangleBuilder.create() // 2
                            .width(size).height(size)
                            .fill(new ImagePattern(side2, 0, 0, size, size, false))
                            .translateX(-0.5 * size)
                            .translateY(0)
                            .rotationAxis(Rotate.X_AXIS)
                            .rotate(90)
                            .build(),
                    RectangleBuilder.create() // 3
                            .width(size).height(size)
                            .fill(new ImagePattern(side3, 0, 0, size, size, false))
                            .translateX(-1 * size)
                            .translateY(-0.5*size)
                            .rotationAxis(Rotate.Y_AXIS)
                            .rotate(90)
                            .build(),
                    RectangleBuilder.create() // 4
                            .width(size).height(size)
                            .fill(new ImagePattern(side4, 0, 0, size, size, false))
                            .translateX(0)
                            .translateY(-0.5*size)
                            .rotationAxis(Rotate.Y_AXIS)
                            .rotate(90)
                            .build(),
                    RectangleBuilder.create() // 5
                            .width(size).height(size)
                            .fill(new ImagePattern(side5, 0, 0, size, size, false))
                            .translateX(-0.5 * size)
                            .translateY(-1*size)
                            .rotationAxis(Rotate.X_AXIS)
                            .rotate(90)
                            .build(),
                    RectangleBuilder.create() // 6
                            .width(size).height(size)
                            .fill(new ImagePattern(side6, 0, 0, size, size, false))
                            .translateX(-0.5 * size)
                            .translateY(-0.5*size)
                            .translateZ(-0.5*size)
                            .build()
            );
        }
    }
}
