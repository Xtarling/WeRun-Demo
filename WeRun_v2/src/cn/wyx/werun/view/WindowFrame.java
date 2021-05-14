package cn.wyx.werun.view;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * @author WYX
 * @date 2021-2-15 - 10:26
 * --------------------------------
 * 缓冲加载游戏界面、加载进度条
 * ### v2改动：
 *  1.切换GameFrame改用新开线程
 *  2.删除start和run方法，WindowFrame窗体改用单线程直接启动
 */
public class WindowFrame extends JFrame {
    JLabel background; //似乎可用JPanel绘制背景，与其它Frame类统一风格？但布局上好像把背景作为一个icon放在屏幕上方，把进度条放在下方
    JProgressBar jdt; //加载进度条

    public WindowFrame() {
        background = new JLabel(new ImageIcon("WeRun_v2/image/hbg.jpg"));
        this.add(BorderLayout.NORTH, background); //放在窗口上面

        jdt = new JProgressBar();
        jdt.setStringPainted(true); //以字符串形式显示加载效果
        jdt.setBackground(Color.ORANGE);
        this.add(BorderLayout.SOUTH, jdt);

        this.setSize(568, 340);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("WeRun_v2/image/115.png").getImage());
        this.setTitle("天天酷跑 (Made by WYX)");
        this.setVisible(true);

        int[] values = {0, 1, 3, 10, 23, 32, 40, 47, 55, 66, 76, 86, 89, 95, 99, 99, 99, 100};
        for (int i = 0; i < values.length; i++) {
            jdt.setValue(values[i]);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        new Thread(() -> {
            new GameFrame();
        }).start();

        dispose();
    }

    //测试用
    public static void main(String[] args) {
        new WindowFrame();
    }
}
