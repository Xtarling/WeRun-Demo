package cn.wyx.werun.view;

import cn.wyx.werun.controller.GamePanel;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * @author WYX
 * @date 2021-2-15 - 18:54
 * --------------------------------
 * 游戏主界面窗体
 */
public class GameFrame extends JFrame {
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 900;

    public GameFrame() {
        GamePanel panel = new GamePanel();
        panel.action();
        this.addKeyListener(panel);
        this.add(panel);

        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("WeRun_v1/image/115.png").getImage());
        this.setTitle("天天酷跑 (Made by WYX)");
        this.setVisible(true);

        while (true) {
            if (panel.isGameOver) {
                dispose();
            }
            try { //为何要延时？
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new GameFrame();
    }
}
