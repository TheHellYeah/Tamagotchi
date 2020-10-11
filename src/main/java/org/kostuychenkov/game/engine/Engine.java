package org.kostuychenkov.game.engine;

import org.kostuychenkov.game.gamestate.GameStateService;
import org.kostuychenkov.game.gamestate.states.MenuState;
import org.kostuychenkov.game.gui.GameWindow;
import org.kostuychenkov.model.connection.DatabaseConnection;
import org.kostuychenkov.model.connection.MySQLConnection;
import org.kostuychenkov.model.repository.PetRepository;
import org.kostuychenkov.model.repository.PetRepositoryImpl;
import org.kostuychenkov.resources.Resources;
import org.kostuychenkov.service.PetService;
import org.kostuychenkov.service.PetServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Центральный класс(фасад), запускает игровые интерфейсы, инициализирует игровой цикл, слушатели, подключает базу данных
 */
public class Engine {

    private Timer timer;
    private GameWindow window;
    private GameStateService gameStateService;
    private PetService petService;

    private final static String MYSQL_PROPERTIES_PATH = "src/main/resources/mysql_db.properties";

    public Engine() {
        timer = new Timer(20, new MainGameLoop());
        window = new GameWindow();
        gameStateService = new GameStateService();
    }

    public void start() {

        databaseInit();
        gameStateService.setState(new MenuState(gameStateService, petService, petService.load()));

        window.addPanel(new GameScreen());
        window.addKeyListener(new Keyboard());
        window.addMouseListener(new Mouse());
        window.createWindow();

        timer.start();
        AudioPlayer.playMusic(Resources.MAIN_THEME, true);
    }

    private void databaseInit() {
        DatabaseConnection databaseConnection = new MySQLConnection(MYSQL_PROPERTIES_PATH);
        databaseConnection.connect();
        PetRepository petRepository = new PetRepositoryImpl(databaseConnection);
        this.petService = new PetServiceImpl(petRepository);
    }

    private class MainGameLoop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            gameStateService.loop();
        }
    }

    private class GameScreen extends JPanel {

        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            gameStateService.render(g);
            repaint();
        }
    }

    private class Keyboard implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            gameStateService.keyPressed(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            gameStateService.keyReleased(e.getKeyCode());
        }

        @Override
        public void keyTyped(KeyEvent e) {}
    }

    private class Mouse extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            gameStateService.mouseClicked(e);
        }
    }
}

