package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visable;
    protected Image image;
    protected ImageIcon ic;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        visable = true;
    }

    BufferedImage bufimg;
    protected void loadImage(String imageName){
        try {
            bufimg = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();

    }

    protected void getImageDimensions(){
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Image getImage(){
        return image;
    }

    public ImageIcon getImageIcon(){
        return ic;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean isVisable(){
        return visable;
    }

    public void setVisable(Boolean visable) {
        this.visable = visable;
    }
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }


}
