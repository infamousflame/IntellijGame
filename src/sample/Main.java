package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Main extends Application {
    PerspectiveCamera camera = new PerspectiveCamera();
    Canvas background = new Canvas(800, 600);
    Canvas character = new Canvas(800, 600);

    GraphicsContext g;
    GraphicsContext g1;

    int gravity = 10;
    //File tempFile = new File("C:\\Users\\Owenl_000\\IdeaProjects\\Game\\src\\sample\\res\\img.png");

    File hero = new File("C:\\Users\\BigBertha\\Documents\\GitHub\\IntellijGame\\src\\sample\\res\\brick.png");

    boolean kLeft, kRight, kUp, kDown, kClick;
    int x, y;
    int same;
    StackPane pane = new StackPane();


    int value = 1;
    double count;
    public void animationCounter(){

        if(count > 20){
            value++;
            count = 0;
        }
        if(value > 2){
            value = 1;
        }
        count++;

    }
    public Image animation(){
        File tempPath = new File("C:\\Users\\BigBertha\\Documents\\GitHub\\IntellijGame\\src\\sample\\res\\hero"+value+".png");
        Image tempImage = new Image(tempPath.toURI().toString());

        return  tempImage;
    }





    public void gravity(){
        y += gravity;
    }


    public void setCanvas(){
        g = background.getGraphicsContext2D();
        g1 = character.getGraphicsContext2D();
    }

    public void start(Stage primaryStage) throws Exception{
        Panel root = new Panel();

        setCanvas();

        pane.getChildren().addAll(character, background);


        Scene scene = new Scene(pane, 800, 600, Color.GREY);


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
        pane.setOnMouseClicked(e ->{
            kClick = true;
            System.out.println("click");

        });

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                checkKey();
                characterDraw(g1);
                character.setTranslateX(x);

                

            }
        }.start();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void characterDraw(GraphicsContext g){
        g.drawImage(getImage("Boron.png"), 100, 100, 100, 100);

    }


    public void checkKey(){
        if(kUp){
            y-= 20;

        }
        if(kDown){
            y+= 20;
        }
        if(kRight){
            x+= 10;
            animationCounter();
        }
        if(kLeft){
            x-= 10;
            animationCounter();
        }
        if(kClick){
            System.out.println("same");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ImageView getImageView(String path){
        //  File tempFile = new File("C:\\Users\\Owenl_000\\IdeaProjects\\Game\\src\\sample\\res\\" + path);
        File tempFile = new File("C:\\Users\\BigBertha\\Documents\\GitHub\\IntellijGame\\src\\sample\\res\\" + path);
        javafx.scene.image.Image image = new Image(tempFile.toURI().toString());
        ImageView viewer = new ImageView(image);
        return viewer;
    }
    public Image getImage(String path){
        File tempFile = new File("C:\\Users\\BigBertha\\Documents\\GitHub\\IntellijGame\\src\\sample\\res\\" + path);
        javafx.scene.image.Image image = new Image(tempFile.toURI().toString());
        return image;
    }
}
