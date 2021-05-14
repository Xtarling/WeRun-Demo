package cn.wyx.werun.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author WYX
 * @date 2021-2-15 - 18:58
 * --------------------------------
 * 宠物
 */
public class Pet {
    public static final int WIDTH = 70;
    public static final int HEIGHT = 60;

    private Image[] images;
    private Image image;
    int index;

    private int x, y;

    public Pet() {
        init();
        image = images[0];
        index = 0;
        x = 300;
        y = 460;
    }

    private void init() { //去掉init?
        images = new Image[6];
        for (int i = 0; i < images.length; i++) {
            try {
                images[i] = ImageIO.read(new File("WeRun_v1/image/d" + (i + 1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintPet(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    public void step() {
        image = images[index++ / 2 % images.length]; //?
//        if (index == images.length) index = 0;
//        image = images[(index++) % images.length];
    }

    public void drop() {
        y++;
        if (y >= 460) y = 460;
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
}
