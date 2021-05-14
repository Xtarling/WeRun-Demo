package cn.wyx.werun.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * @author WYX
 * @date 2021-2-15 - 9:41
 * --------------------------------
 * 主菜单界面，涵盖“开始游戏”、“帮助”、“退出”按钮，并实现各自对应的功能
 */
public class MainFrame extends JFrame implements MouseListener {
    JLabel start, help, exit;

    public MainFrame() {
        start = new JLabel(new ImageIcon("WeRun_v1/image/hh1.png"));
        start.setBounds(350, 320, 150, 40);
        start.setEnabled(false); //按钮为灰色
        start.addMouseListener(this);
        this.add(start);

        help = new JLabel(new ImageIcon("WeRun_v1/image/hh2.png"));
        help.setBounds(350, 420, 150, 40);
        help.setEnabled(false);
        help.addMouseListener(this);
        this.add(help);

        exit = new JLabel(new ImageIcon("WeRun_v1/image/hh3.png"));
        exit.setBounds(350, 520, 150, 40);
        exit.setEnabled(false);
        exit.addMouseListener(this);
        this.add(exit);

        //设置背景图片
        MainPanel panel = new MainPanel();
        this.add(panel);

        //设置窗体属性
        this.setSize(1200, 730);
        this.setLocationRelativeTo(null); //居中
        this.setUndecorated(true); //显示边框
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("WeRun_v1/image/115.png").getImage());
        this.setTitle("天天酷跑 (Made by WYX)");
        this.setVisible(true);
    }

    class MainPanel extends JPanel {
        Image background;
        public MainPanel() {
            try {
                background = ImageIO.read(new File("WeRun_v1/image/main.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(background, 0, 0, 1200, 730, null);
        }
    }

    //鼠标监听事件
    @Override
    public void mouseClicked(MouseEvent e) { //鼠标光标点击start/help/exit按钮对应的功能
        if (e.getSource().equals(start)) {
            new WindowFrame().start(); //new WindowFrame(); ?
            dispose();
        }
        else if (e.getSource().equals(help)) {
            JOptionPane.showMessageDialog(null, "有疑问请联系开发者：WYX。联系方式：略");
        }
        else if (e.getSource().equals(exit)) {
            dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) { //光标移动到按钮上
        if (e.getSource().equals(start)) {
            start.setEnabled(true);
        }
        else if (e.getSource().equals(help)) {
            help.setEnabled(true);
        }
        else if (e.getSource().equals(exit)) {
            exit.setEnabled(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) { //光标从按钮上移出
        if (e.getSource().equals(start)) {
            start.setEnabled(false);
        }
        else if (e.getSource().equals(help)) {
            help.setEnabled(false);
        }
        else if (e.getSource().equals(exit)) {
            exit.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
