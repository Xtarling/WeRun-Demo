package cn.wyx.werun.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @author WYX
 * @date 2021-2-14 - 21:03
 * --------------------------------
 * 登录界面：用户名输入框、密码输入框、登录取消按钮，以及各自对应的功能
 * ### v2改动：
 *  1.切换MainFrame改用新开线程
 */
public class LoginFrame extends JFrame {
    JLabel userNameLabel;           //用户名变量（文本）
    JLabel passwordLabel;          //密码变量（文本）
    JTextField userNameField;       //用户名输入框（文本输入框）
    JPasswordField passwordField;  //密码输入框（文本输入框）
    JButton login, cancel;      //登录、取消按钮

    public LoginFrame() {
        //设置显示“用户名”、“密码”、用户名输入框、密码输入框
        userNameLabel = new JLabel("用户名"); //设置显示内容
        userNameLabel.setFont(new Font("微软雅黑", Font.BOLD, 18)); //设置显示字体
        userNameLabel.setBounds(20, 220, 100, 30); //设置显示位置和大小
        this.add(userNameLabel); //添加至界面

        passwordLabel = new JLabel("密 码");
        passwordLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        passwordLabel.setBounds(20, 280, 100, 30);
        this.add(passwordLabel);

        userNameField = new JTextField(); //文本输入框对象
        userNameField.setBounds(80, 220, 100, 30);
        userNameField.setBorder(BorderFactory.createLoweredBevelBorder()); //设置输入框凹陷效果
        userNameField.setOpaque(false); //设置输入框透明效果
        this.add(userNameField);

        passwordField = new JPasswordField(); //密码输入框对象
        passwordField.setBounds(80, 280, 100, 30);
        passwordField.setBorder(BorderFactory.createLoweredBevelBorder());
        passwordField.setOpaque(false);
        this.add(passwordField);

        //设置登录和取消按钮
        login = new JButton("登录");
        login.setBounds(45, 350, 60, 36);
        login.addActionListener(new ActionListener() { //设置事件监听器
            @Override
            public void actionPerformed(ActionEvent e) { //检查用户名和密码是否正确
                String userName = userNameField.getText();
                String password = LoginFrame.this.passwordField.getText();

                if ("player".equals(userName) && "123".equals(password)) {
                    JOptionPane.showMessageDialog(null, "欢迎" + userName + "来到天天酷跑游戏！");
                    new Thread(() -> {
                        new MainFrame();
                    }).start();
                    dispose();
                }
                else if ("".equals(userName) || "".equals(password)) {
                    JOptionPane.showMessageDialog(null, "用户名/密码不能为空，请重新输入！");
                }
                else {
                    JOptionPane.showMessageDialog(null, "用户名/密码输入错误，请重新输入！");
                }
            }
        });
        this.add(login);

        cancel = new JButton("取消");
        cancel.setBounds(135, 350, 60, 36);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                dispose();
                System.exit(0);
            }
        });
        this.add(cancel);

        //创建并添加背景面板
        LoginPanel panel = new LoginPanel();
        this.add(panel);

        //设置登录界面的基本属性
        this.setSize(900, 530);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true); //显示边框

        //设置窗体logo图标和标题
        this.setIconImage(new ImageIcon("WeRun_v2/image/115.png").getImage());
        this.setTitle("天天酷跑 (Made by Xtarling)");
        this.setVisible(true);
    }

    class LoginPanel extends JPanel {
        Image background;

        public LoginPanel() {
            try {
                background = ImageIO.read(new File("WeRun_v2/image/login.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(background, 0, 0, 900, 530, null);
        }
    }

    //程序主入口
    public static void main(String[] args) {
        new LoginFrame();
    }
}
