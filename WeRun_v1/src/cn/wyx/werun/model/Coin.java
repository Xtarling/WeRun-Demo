package cn.wyx.werun.model;

import cn.wyx.werun.view.GameFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author WYX
 * @date 2021-2-15 - 19:00
 * --------------------------------
 * 金币
 * (可把这些画面元素搞个抽象类出来？)
 */
public class Coin {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    private Image image;
    private int x, y;
    private int speed;

    public Coin() {
        Random random = new Random();
        try {
            image = ImageIO.read(new File("WeRun_v1/image/" + (random.nextInt(6) + 21) + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        x = GameFrame.WIDTH + 10;
        y = random.nextInt(600);
        speed = 20;
    }

    public void paintCoin(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    public void step() {
        x -= speed;
    }

    public boolean outOfBounds() {
        return x <= -WIDTH;
    }

    //Getter & Setter    //random get set?
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
