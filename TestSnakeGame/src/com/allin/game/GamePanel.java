package com.allin.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * @author: allin
 */
public class GamePanel extends JPanel {
    // 定义两个数组,用来存放蛇的x轴和y轴坐标
    int[] snakeX = new int[200];
    int[] snakeY = new int[200];
    // 定义一个变量, 用于记录蛇身体的长度
    int length;
    // 定义蛇的行走方向
    String direction;
    // 定义一个变量, 记录游戏的运行状态
    boolean isStart = false;
    // 定义一个定时器
    Timer timer;
    // 定义食物的坐标
    int foodX;
    int foodY;
    // 积分
    int score;
    // 定义一个变量,用于记录小蛇的死亡状态
    boolean isDie = false; // 默认情况下,小蛇非死亡状态

    public GamePanel() {
        init();
        // 将焦点定位在当前的画板上
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_SPACE) {
                    System.out.println("点击了空格");
                    if (isDie) {
                        isDie = false;
                        init();
                    } else {
                        isStart = !isStart;
                        repaint();

                    }
                }
                // 监听向上箭头
                if (keyCode == KeyEvent.VK_UP) {
                    direction = "U";
                }
                // 监听向下箭头
                if (keyCode == KeyEvent.VK_DOWN) {
                    direction = "D";
                }
                // 监听向右箭头
                if (keyCode == KeyEvent.VK_RIGHT) {
                    direction = "R";
                }
                // 监听向左箭头
                if (keyCode == KeyEvent.VK_LEFT) {
                    direction = "L";
                }
            }
        });
        // 初始化定时器
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStart && !isDie) {
                    // 游戏开始的时候,蛇才动
                    // 后一节蛇身走到前一节蛇身的位置上
                    for (int i = length - 1; i > 0; i--) {
                        snakeX[i] = snakeX[i - 1];
                        snakeY[i] = snakeY[i - 1];
                    }
                    if ("R".equalsIgnoreCase(direction)) {
                        snakeX[0] += 25;
                    }
                    if ("L".equalsIgnoreCase(direction)) {
                        snakeX[0] -= 25;
                    }
                    if ("U".equalsIgnoreCase(direction)) {
                        snakeY[0] -= 25;
                    }
                    if ("D".equalsIgnoreCase(direction)) {
                        snakeY[0] += 25;
                    }
                    if (snakeX[0] > 750) {
                        snakeX[0] = 20;
                    }
                    if (snakeX[0] < 20) {
                        snakeX[0] = 750;
                    }
                    if (snakeY[0] > 725) {
                        snakeY[0] = 60;
                    }
                    if (snakeY[0] < 60) {
                        snakeY[0] = 725;
                    }
                    // 检测碰撞
                    // 如果蛇头的坐标与食物的坐标重叠, 那么认为是碰撞
                    if (Math.abs(snakeX[0] - foodX) <= 10 && Math.abs(snakeY[0] - foodY) <= 10) {
                        // 首先蛇身增长1
                        length++;
                        // 然后食物改变位置
                        //食物坐标改变：随机生成坐标 --》细节：坐标必须是25的倍数
                        /*
                        [25,750] -> [1,30]*25
                        [1,30]
                        Math.random() -> [0.0,1.0)
                        Math.random()*30 -> [0.0,30.0)
                        (int)(Math.random()*30) -> [0,29]
                        (int)(Math.random()*30)+1 -> [1,30]
                         */
                        foodX = ((int) (Math.random() * 30) + 1) * 25;//[25,750]
                        /*
                        [100,725] -> [4,29]*25
                        [4,29]->[0,25]+4
                        [0,25]
                        new Random().nextInt(26) -> [0,26) ->[0,25]
                         */
                        foodY = (new Random().nextInt(26) + 4) * 25;//[100,725]
                        score += 10;
                    }

                    // 死亡检测: 蛇头与蛇身任意一节碰撞, 则小蛇死亡
                    for (int i = 1; i < length; i++) {
                        if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                            isDie = true;
                            break;
                        }
                    }
                    repaint();
                }
            }
        });
        // 启动定时器
        timer.start();
    }

    public void init() {
        length = 3;
        // 蛇头坐标
        snakeX[0] = 175;
        snakeY[0] = 275;
        // 初始化第一节蛇身的坐标
        snakeX[1] = 150;
        snakeY[1] = 275;
        // 初始化第二节蛇身的坐标
        snakeX[2] = 125;
        snakeY[2] = 275;
        // 初始化行走方向
        direction = "R"; // R L U D
        // 初始化食物坐标
        foodX = 300;
        foodY = 300;
    }

    /*
    paintComponent这个方法比较特殊,属于图形版的main方法,会自动调用
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 给面板设置一个背景色
        this.setBackground(new Color(191, 203, 188));

        Images.headerImg.paintIcon(this, g, 10, 10);

        //调整画笔的颜色
        g.setColor(new Color(231, 247, 195));
        // 画一个矩形
        g.fillRect(10, 70, 770, 675);

        // 画静态小蛇
        // 画蛇头的方向
        if ("R".equalsIgnoreCase(direction)) {
            Images.rightImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if ("L".equalsIgnoreCase(direction)) {
            Images.leftImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if ("U".equalsIgnoreCase(direction)) {
            Images.upImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if ("D".equalsIgnoreCase(direction)) {
            Images.downImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

        // 画蛇身
        for (int i = 1; i < length; i++) {
            Images.bodyImg.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        // 当游戏暂停是, 需要在屏幕中间输入一行文字,提醒
        if (!isStart) {
            g.setColor(new Color(82, 102, 239, 109));
            g.setFont(new Font("微软雅黑", Font.BOLD, 24));
            g.drawString("游戏暂停,请按空格键开始!", 250, 330);
        }

        // 画食物
        Images.foodImg.paintIcon(this, g, foodX, foodY);

        // 画积分
        g.setColor(new Color(255, 255, 255, 255));
        g.setFont(new Font("微软雅黑", Font.BOLD, 24));
        g.drawString("积分: " + score, 620, 40);

        // 画死亡状态
        if (isDie) {
            g.setColor(new Color(214, 121, 121, 255));
            g.setFont(new Font("微软雅黑", Font.BOLD, 24));
            g.drawString("小蛇已死亡,请按空格键重新开始游戏", 200, 330);
        }
    }
}
