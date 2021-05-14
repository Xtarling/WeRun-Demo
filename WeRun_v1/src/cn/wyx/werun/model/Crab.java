package cn.wyx.werun.model;

import cn.wyx.werun.view.GameFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author WYX
 * @date 2021-2-15 - 18:58
 * --------------------------------
 * 障碍物：寄居蟹
 */
public class Crab {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 110;

    private Image[] images;
    private Image image;
    int index;

    private int x, y;
    private int speed;

    public Crab() {
        images = new Image[2];
        try {
            images[0] = ImageIO.read(new File("WeRun_v1/image/a2.png"));
            images[1] = ImageIO.read(new File("WeRun_v1/image/a4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = images[0];
        index = 0;
        x = GameFrame.WIDTH + 100;
        y = 580;
        speed = 30;
    }

    public void paintCrab(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    public void step() {
        image = images[index++ / 5 % images.length];
        x -= speed;
    }

    public boolean outOfBounds() {
        return x <= -WIDTH;
    }

    //Getter & Setter
    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
