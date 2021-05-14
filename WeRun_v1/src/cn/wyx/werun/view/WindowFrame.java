package cn.wyx.werun.view;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * @author WYX
 * @date 2021-2-15 - 10:26
 * --------------------------------
 * 缓冲加载游戏界面、加载进度条
 */
public class WindowFrame extends JFrame implements Runnable {
    JLabel background; //用JPanel统一绘制背景？
    JProgressBar jdt; //加载进度条

    public WindowFrame() {
        background = new JLabel(new ImageIcon("WeRun_v1/image/hbg.jpg"));
        this.add(BorderLayout.NORTH, background); //放在窗口上面

        jdt = new JProgressBar();
        jdt.setStringPainted(true); //以字符串形式显示加载效果
        jdt.setBackground(Color.ORANGE);
        this.add(BorderLayout.SOUTH, jdt);

        this.setSize(568, 340);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("WeRun_v1/image/115.png").getImage());
        this.setTitle("天天酷跑 (Made by WYX)");
        this.setVisible(true);

//        int[] values = {0, 1, 3, 10, 23, 32, 40, 47, 55, 66, 76, 86, 89, 95, 99, 99, 99, 100};
//        for (int i = 0; i < values.length; i++) {
//            jdt.setValue(values[i]);
//            try {
//                TimeUnit.MILLISECONDS.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        new GameFrame();
//        dispose();
    }

    public void start() {
//        WindowFrame frame = new WindowFrame();
//        Thread t = new Thread(frame);
//        t.start();
        new Thread(new WindowFrame()).start();
//        new Thread(this).start();
        dispose();
    }

    @Override
    public void run() {
        int[] values = {0, 1, 3, 10, 23, 32, 40, 47, 55, 66, 76, 86, 89, 95, 99, 99, 99, 100};
        for (int i = 0; i < values.length; i++) {
            jdt.setValue(values[i]);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new GameFrame();
        dispose();
    }

    public static void main(String[] args) { //尝试只用一个线程写出来？
        new WindowFrame().start();
//        new WindowFrame();
//        new WindowFrame().run(); //new WindowFrame(); ?
    }
}
