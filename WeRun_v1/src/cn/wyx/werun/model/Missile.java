package cn.wyx.werun.model;

import cn.wyx.werun.view.GameFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author WYX
 * @date 2021-2-15 - 18:59
 * --------------------------------
 * 障碍物：导弹
 */
public class Missile {
    public static final int WIDTH = 150;
    public static final int HEIGHT = 70;

    private Image image;
    private int x, y;
    private int speed;

    public Missile() {
        try {
            image = ImageIO.read(new File("WeRun_v1/image/daodan.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        x = GameFrame.WIDTH + 1000; //1000还是100？
        y = 450;
        speed = 25;
    }

    public void paintMissile(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    public void step() {
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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
