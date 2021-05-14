package cn.wyx.werun.model;

import cn.wyx.werun.view.GameFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author WYX
 * @date 2021-2-15 - 18:57
 * --------------------------------
 * 玩家小人
 */
public class Person {
    public static final int WIDTH = 120; //玩家小人图片的宽、高
    public static final int HEIGHT = 120;

    private Image[] images; //每帧图片
    private Image image; //当前显示的图片
    int index; //图片序号

    private int x, y; //玩家位置坐标
    private int score; //玩家得分
    private int distance; //玩家跑酷距离

    public Person() {
        init();
        image = images[0];
        index = 0;
        x = 90; //玩家初始位置坐标（脚踩地板）
        y = 580;
        score = 0;
        distance = 0;
    }

    private void init() {
        images = new Image[9];
        for (int i = 0; i < images.length; i++) {
            try {
                images[i] = ImageIO.read(new File("WeRun_v2/image/" + (i + 1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //绘制
    public void paintPerson(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    //移动
    public void step() {
        image = images[index++ / 3 % images.length]; //?
        distance += 2;
    }

    //下落
    public void drop() {
        y += 5;
        if (y >= 580) y = 580;
    }

    //判断是否越界
    public boolean outOfBounds() {
        return x >= GameFrame.WIDTH || x <= -WIDTH;
    }

    public int getTotalScore() {
        return (int) (score * 10 + distance * 0.6);
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
