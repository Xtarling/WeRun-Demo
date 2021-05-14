package cn.wyx.werun.model;

import cn.wyx.werun.view.GameFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author WYX
 * @date 2021-2-15 - 18:59
 * --------------------------------
 * 障碍物：鱼钩
 */
public class Hook {
    public static final int WIDTH = 150;
    public static final int HEIGHT = 350;

    private Image[] images;
    private Image image;

    private int x, y;

    public Hook() { //可优化成只随机读取一张图，而不是全读进来再随机选一张？
        Random random = new Random();
        images = new Image[4];
        try {
            images[0] = ImageIO.read(new File("WeRun_v2/image/11.png"));
            images[1] = ImageIO.read(new File("WeRun_v2/image/12.png"));
            images[2] = ImageIO.read(new File("WeRun_v2/image/13.png"));
            images[3] = ImageIO.read(new File("WeRun_v2/image/14.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = images[random.nextInt(4)];
        x = GameFrame.WIDTH + 1500;
        y = 0;
    }

    public void paintHook(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    public void step() {
        x -= 20; //统一设置speed变量？
    }

    public boolean outOfBounds() {
        return x <= -WIDTH;
    }

    //Getter & Setter
    public static int getWIDTH() {
        return WIDTH;
    } //可删掉？

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
}
