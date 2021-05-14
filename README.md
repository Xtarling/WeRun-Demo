# 《天天酷跑》小游戏—Java实现

**Author: Xtarling**

## 简介

本项目使用Java的图形相关类库实现简化版《天天酷跑》小游戏。设计模式选用MVC模式。

参考源码：[HueyM/Projects (github.com)](https://github.com/HueyM/Projects)

## 项目结构

```shell
\---src
    \---cn
        \---wyx
            \---werun
                +---controller
                |       GamePanel.java
                |
                +---model
                |       Coin.java
                |       Crab.java
                |       Hook.java
                |       Missile.java
                |       Person.java
                |       Pet.java
                |
                \---view
                        EndFrame.java
                        GameFrame.java
                        LoginFrame.java
                        MainFrame.java
                        WindowFrame.java
```

## 使用说明

- 程序主入口为LoginFrame.java中的main函数。主程序执行流为LoginFrame -> MainFrame -> WindowFrame -> GameFrame -> EndFrame。

  - 登录账号：player
  - 密码：123

  若不想每次游玩都登录，则可选择任一个Frame类中的main函数开始执行，以跳过前面的步骤。

- 操作：键盘上下左右移动小人躲避障碍物，空格键暂停。



祝各位玩得开心！😊

