package sample;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.util.Random;

public class Bullet{
    private int x, y;
    private int yOffset = 20;
    private int xOffset = 20;
    public boolean visible = true;
    public boolean deleteBullet;
    boolean up, down;
    int thresh;
    File file = new File("C:\\Users\\BigBertha\\Documents\\GitHub\\IntellijGame\\src\\sample\\res\\brick.png");
    Image image = new Image(file.toURI().toString());
    ImageView viewer = new ImageView(image);
    public Bullet(int tempX, int tempY, int width, int height, int recoil){
        x = tempX + xOffset;
        y = tempY + yOffset;
        Rectangle rect = new Rectangle(tempX, tempY, width, height);
        recoil(recoil);

    }
    public ImageView getImage(){
        return viewer;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean getVisible(){
        return visible;
    }
    public void delete(){

    }
    public void move(boolean bool){



        x += 10;

        if(x < 100000){
            visible = true;
        }else{
            visible = false;
        }
        if(up){
            y -= thresh;
        }
        if(down){
            y += thresh;
        }

    }

    public void recoil(int intensity){
        Random rn = new Random();
        int x = rn.nextInt(2);
        thresh = rn.nextInt(intensity);
        if(x == 0){
            down = true;
        }else{
            up = true;
        }
    }

}
