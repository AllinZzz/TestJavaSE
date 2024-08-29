package com.allin.game;

import javax.swing.*;
import java.awt.*;

/**
 * @author: allin
 */
public class StartGame {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        // 设置标题
        jf.setTitle("浩然贪吃蛇");
        // 设置窗体弹出的坐标和宽高
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        jf.setBounds((width - 800) / 2, (height -800) / 2, 800, 800);
        // 设置窗体大小不可调节
        jf.setResizable(false);
        // 设置关闭游戏时, 程序退出
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 默认情况下, 窗体是隐藏的,需要手动设为显示
        jf.setVisible(true);
    }
}
