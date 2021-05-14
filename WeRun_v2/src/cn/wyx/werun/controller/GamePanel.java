package cn.wyx.werun.controller;

import cn.wyx.werun.model.*;
import cn.wyx.werun.view.EndFrame;
import cn.wyx.werun.view.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author WYX
 * @date 2021-2-15 - 19:00
 * --------------------------------
 * 游戏主面板，实现核心逻辑
 * 主要功能：
 *      1. 背景图片滚动
 *      2. 玩家动态效果
 *      3. 各种障碍物的出现
 *      4. 碰撞逻辑
 *      5. 暂停、继续逻辑
 *      6. 结束逻辑
 */
public class GamePanel extends JPanel implements KeyListener {
    Image background;
    Image score;
    Image pause;
    Image proceed;

    Person person;
    Pet pet; //宠物
    Crab[] crabs = {}; //寄居蟹  //所有数组改用list或者双端队列存储？
    Missile[] missiles = {}; //导弹
    Hook[] hooks = {}; //鱼叉
    Coin[] coins = {}; //金币

    private int backgroundX = 0; //背景图片绘制位置
    private int seed = 0; //障碍物生成的伪随机种子
    public boolean gameOver = false;
    boolean isPause = false; //是否暂停标志。true表示当前处于暂停状态

    public GamePanel() {
        person = new Person();
        pet = new Pet();
        try {
            background = ImageIO.read(new File("WeRun_v2/image/cc.png"));
            score = ImageIO.read(new File("WeRun_v2/image/a12.png"));
            pause = ImageIO.read(new File("WeRun_v2/image/b2.png"));
            proceed = ImageIO.read(new File("WeRun_v2/image/b1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    开始游戏
    每帧画面按顺序执行以下四步：
        1.生成画面元素（螃蟹、导弹、鱼钩、金币）
        2.计算各元素移动后的位置。移动速度的单位为(像素/帧)
        3.碰撞检测与处理
        4.检测游戏是否结束
        5.绘制画面
        6.线程sleep
    若键盘检测到按了空格键，则切换为暂停，下一帧开始停止画面的更新，仅重复绘制相同内容。
    若第4步检测到游戏结束，则终止循环。
     */
    public void action() {
        new Thread(() -> { //为何要另开一个线程？
            while (!gameOver) {
                if (!isPause) {
                    generateElem();
                    stepAction();
                    collideDetect();
                    gameOverAction();
                }
                repaint();
                try {
                    Thread.sleep(40); //两帧画面的时间间隔。用于调整游戏帧数/速率
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //生成障碍物
    public void generateElem() {
        seed++;
        if (seed % 100 == 0) {
            Crab crab = new Crab();
            crabs = Arrays.copyOf(crabs, crabs.length + 1);
            crabs[crabs.length - 1] = crab;

            Missile missile = new Missile();
            missiles = Arrays.copyOf(missiles, missiles.length + 1);
            missiles[missiles.length - 1] = missile;

            Hook hook = new Hook();
            hooks = Arrays.copyOf(hooks, hooks.length + 1);
            hooks[hooks.length - 1] = hook;
        }
        if (seed % 15 == 0) {
            Coin coin = new Coin();
            coins = Arrays.copyOf(coins, coins.length + 1);
            coins[coins.length - 1] = coin;
        }
    }

    //移动
    public void stepAction() {
        person.step();
        person.drop();
        pet.step();
        pet.drop();

        for (int i = 0; i < crabs.length; i++) { //若改用list存放，则这里也要改
            crabs[i].step();
            if (crabs[i].outOfBounds()) { //若越界则删除
                crabs[i] = crabs[crabs.length - 1];
                crabs = Arrays.copyOf(crabs, crabs.length - 1);
            }
        }

        for (int i = 0; i < missiles.length; i++) {
            missiles[i].step();
            if (missiles[i].outOfBounds()) {
                missiles[i] = missiles[missiles.length - 1];
                missiles = Arrays.copyOf(missiles, missiles.length - 1);
            }
        }

        for (int i = 0; i < hooks.length; i++) {
            hooks[i].step();
            if (hooks[i].outOfBounds()) {
                hooks[i] = hooks[hooks.length - 1];
                hooks = Arrays.copyOf(hooks, hooks.length - 1);
            }
        }

        for (int i = 0; i < coins.length; i++) {
            coins[i].step();
            if (coins[i].outOfBounds()) {
                coins[i] = coins[coins.length - 1];
                coins = Arrays.copyOf(coins, coins.length - 1);
            }
        }
    }

    //碰撞检测和处理
    public void collideDetect() {
        for (int i = 0; i < crabs.length; i++) { //矩形碰撞检测
            if (person.getX() + Person.WIDTH >= crabs[i].getX() && person.getX() <= crabs[i].getX() + Crab.WIDTH &&
                person.getY() + Person.HEIGHT >= crabs[i].getY() && person.getY() <= crabs[i].getY() + Crab.HEIGHT) {
                if (person.getX() + Person.WIDTH <= crabs[i].getX() + Crab.WIDTH) {
                    person.setX(crabs[i].getX() - Crab.WIDTH); //?
                }
                else {
                    person.setX(crabs[i].getX() + Crab.WIDTH); //?
                }
//                if (person.getX() < crabs[i].getX()) { //也许可以这么判断：左边界和左边界判断，右边界和右边界判断（好像不太行，因为必须是二元判断）
//                    person.setX(crabs[i].getX() - Crab.WIDTH);
//                }
//                else if (person.getX() + Person.WIDTH > crabs[i].getX() + Crab.WIDTH) {
//                    person.setX(crabs[i].getX() + Crab.WIDTH);
//                }
            }
        }
        for (int i = 0; i < missiles.length; i++) {
            if (person.getX() + Person.WIDTH >= missiles[i].getX() && person.getX() <= missiles[i].getX() + Missile.WIDTH &&
                person.getY() + Person.HEIGHT >= missiles[i].getY() && person.getY() <= missiles[i].getY() + Missile.HEIGHT) {
                if (person.getX() + Person.WIDTH <= missiles[i].getX() + Missile.WIDTH) {
                    person.setX(missiles[i].getX() - Missile.WIDTH); //?
                }
                else {
                    person.setX(missiles[i].getX() + Missile.WIDTH); //?
                }
            }
        }
        for (int i = 0; i < hooks.length; i++) { // <= ?
            if (person.getX() + Person.WIDTH >= hooks[i].getX() && person.getX() <= hooks[i].getX() + Hook.WIDTH &&
                person.getY() + Person.HEIGHT >= hooks[i].getY() && person.getY() <= hooks[i].getY() + Hook.HEIGHT) {
                if (person.getX() + Person.WIDTH <= hooks[i].getX() + Hook.WIDTH) {
                    person.setX(hooks[i].getX() - Hook.WIDTH); //?
                }
                else {
                    person.setX(hooks[i].getX() + Hook.WIDTH); //?
                }
            }
        }
        for (int i = 0; i < coins.length; i++) { //碰到金币则加分
            if (person.getX() + Person.WIDTH >= coins[i].getX() && person.getX() <= coins[i].getX() + Coin.WIDTH &&
                person.getY() + Person.HEIGHT >= coins[i].getY() && person.getY() <= coins[i].getY() + Coin.HEIGHT) {
                if (person.getX() + Person.WIDTH <= coins[i].getX() + Coin.WIDTH) { //?
                    coins[i] = coins[coins.length - 1];
                    coins = Arrays.copyOf(coins, coins.length - 1);
                    person.setScore(person.getScore() + 10);
                }
            }
        }
    }

    //游戏结束
    public void gameOverAction() {
        if (person.outOfBounds()) {
            gameOver = true;
            new EndFrame(person);
//            person = new Person(); //?有啥用
//            crabs = new Crab[]{};
//            missiles = new Missile[]{};
        }
    }

    //绘图
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //绘制背景（交替滚动形式）
        if (!isPause) backgroundX -= 20; //设置speed变量？
        g.drawImage(background, backgroundX, 0, GameFrame.WIDTH, GameFrame.HEIGHT, null);
        g.drawImage(background, backgroundX + GameFrame.WIDTH, 0, GameFrame.WIDTH, GameFrame.HEIGHT, null);
        if (backgroundX <= -GameFrame.WIDTH) backgroundX = 0;

        //绘制各个元素
        person.paintPerson(g);
        pet.paintPet(g);
        for (Crab crab : crabs) {
            crab.paintCrab(g);
        }
        for (Missile missile : missiles) {
            missile.paintMissile(g);
        }
        for (Hook hook : hooks) {
            hook.paintHook(g);
        }
        for (Coin coin : coins) {
            coin.paintCoin(g);
        }

        //绘制HUD和游戏数据
        g.drawImage(score, 120, 50, null);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("宋体", Font.BOLD, 30));
        g.drawString("玩家得分：" + person.getScore(), 133, 95);
        if (!isPause) {
            g.drawImage(proceed, 200, 800, 90, 90, null);
        }
        else {
            g.drawImage(pause, 200, 800, 90, 90, null);
        }
    }

    //键盘监听事件：玩家移动和暂停
    @Override
    public void keyPressed(KeyEvent e) {
        int x = person.getX();
        int y = person.getY();
        int petX = pet.getX();
        int petY = pet.getY();

        if (e.getKeyCode() == KeyEvent.VK_UP && y > 10 && petY > 10) {
            person.setY(y - 25); //前后左右速度都考虑用speed变量？
            pet.setY(y - 25);
//            pet.setY(petY - 25);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && y < 560 && petY < 560) {
            person.setY(y + 30);
            pet.setY(y - 30);
//            pet.setY(petY + 30);
//            barrs_2.setY(petY - 30);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && x > 0) {
            person.setX(x - 30);
            pet.setX(petX - 30);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // ?
            person.setX(x + 22);
            pet.setX(petX + 22);
            if (x >= GameFrame.WIDTH - Person.WIDTH) person.setX(GameFrame.WIDTH - Person.WIDTH);
            if (petX >= GameFrame.WIDTH - Pet.WIDTH) pet.setX(GameFrame.WIDTH - Pet.WIDTH);
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) isPause = !isPause;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
