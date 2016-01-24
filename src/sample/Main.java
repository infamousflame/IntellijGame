package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class Main extends Application {
    PerspectiveCamera camera = new PerspectiveCamera();
    ImageView sprite1;
    File tempFile = new File("C:\\Users\\Owenl_000\\IdeaProjects\\Game\\src\\sample\\res\\img.png" );
    Image image = new Image(tempFile.toURI().toString());
    public ImageView getImageView(String path){
        File tempFile = new File("C:\\Users\\Owenl_000\\IdeaProjects\\Game\\src\\sample\\res\\" + path);
        javafx.scene.image.Image image = new Image(tempFile.toURI().toString());
        ImageView viewer = new ImageView(image);
        return viewer;
    }


    boolean kLeft, kRight, kUp, kDown;
    int x, y;


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Kappa");
        Panel root = new Panel();
        StackPane pane = new StackPane();

        sprite1 = getImageView("Boron.png");
        Group sprite = new Group();

        Scene scene = new Scene(pane, 800, 600, Color.GREENYELLOW);
        pane.getChildren().add(sprite);
        pane.getChildren().add(sprite1);
        pane.setOnMouseClicked(e ->{
            sprite1.setImage(image);
        });

        checkKey();
        scene.setOnKeyPressed(event -> {
            switch(event.getCode()){
                case UP: kUp = true; break;
                case DOWN: kDown = true; break;
                case LEFT: kLeft = true; break;
                case RIGHT: kRight = true; break;

            }
        });
        scene.setOnKeyReleased(event -> {

            switch(event.getCode()){
                case UP: kUp = false; break;
                case DOWN: kDown = false; break;
                case LEFT: kLeft = false; break;
                case RIGHT: kRight = false; break;
            }
        });

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                scene.setCamera(camera);
                checkKey();
                camera.setTranslateX(x);
                
            }
        }.start();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void checkKey(){
        if(kUp){
            y-= 20;

        }
        if(kDown){
            y+= 20;
        }
        if(kRight){
            x+= 20;
        }
        if(kLeft){
            x-= 20;
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
