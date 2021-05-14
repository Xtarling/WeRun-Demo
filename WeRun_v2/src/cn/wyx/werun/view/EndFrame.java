package cn.wyx.werun.view;

import cn.wyx.werun.model.Person;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * @author WYX
 * @date 2021-2-15 - 22:40
 * --------------------------------
 */
public class EndFrame extends JFrame implements MouseListener {
    JLabel again, back, exit; //按钮：再来一次、返回主菜单、退出

    public EndFrame(Person person) {
        again = new JLabel(new ImageIcon("WeRun_v2/image/hh5.png"));
        again.setBounds(520, 622, 60, 25);
        again.addMouseListener(this);
        this.add(again);

        back = new JLabel(new ImageIcon("WeRun_v2/image/hh6.png"));
        back.setBounds(520, 722, 60, 25);
        back.addMouseListener(this);
        this.add(back);

        exit = new JLabel(new ImageIcon("WeRun_v2/image/hh3.png"));
        exit.setBounds(520, 822, 60, 25);
        exit.addMouseListener(this);
        this.add(exit);

        EndPanel end = new EndPanel(person);
        this.add(end);

        this.setSize(1500, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setIconImage(new ImageIcon("WeRun_v2/image/115.png").getImage());
        this.setTitle("天天酷跑 (Made by WYX)");
        this.setVisible(true);
    }


    class EndPanel extends JPanel {
        Image background;
        Person p;

        public EndPanel(Person person) {
            p = person;
            try {
                background = ImageIO.read(new File("WeRun_v2/image/chou.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(background, 0, 0, 1500, 900, null);

            g.setColor(Color.CYAN);
            g.setFont(new Font("宋体", Font.BOLD, 30));
            g.drawString(p.getScore() + "", 1110, 705);
            g.drawString(p.getDistance() + "", 1110, 622);

            g.setColor(Color.ORANGE);
            g.setFont(new Font("宋体", Font.BOLD, 50));
            g.drawString(p.getTotalScore() + "", 1075, 500);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(again)) {
            new Thread(() -> {
                new WindowFrame();
            }).start();
            dispose();
        }
        else if (e.getSource().equals(back)) {
            new Thread(() -> {
                new MainFrame();
            }).start();
            dispose();
        }
        else if (e.getSource().equals(exit)) {
//            dispose();
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
