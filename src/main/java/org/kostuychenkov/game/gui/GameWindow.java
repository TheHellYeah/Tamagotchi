package org.kostuychenkov.game.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Игровое окно(панель самого верхнего уровня). Тут напрямую подключаются все слушатели и другие компоненты Swing.
 */
public class GameWindow {

    private JFrame frame;
    private JPanel panel;

    public GameWindow() {
        frame = new JFrame(WindowSettings.TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public void addPanel(JPanel panel) {
        this.panel = panel;
        this.panel.setPreferredSize(new Dimension(WindowSettings.WIDTH, WindowSettings.HEIGHT));
        this.panel.setFocusable(true);
        this.panel.requestFocusInWindow();
    }

    public void addKeyListener(KeyListener listener) {
        this.panel.addKeyListener(listener);
    }

    public void addMouseListener(MouseListener listener) {
        this.panel.addMouseListener(listener);
    }

    public void createWindow() {
        this.frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

}
