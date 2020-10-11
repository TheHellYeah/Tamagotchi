package org.kostuychenkov.game.gamestate;

import org.kostuychenkov.service.PetService;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Класс, отвечающий за переключение игровых состояний(меню, в игре и т.д.)
 */
public class GameStateService {

    private GameState state;

    public void setState(GameState state) {
        this.state = state;
    }

    public void loop() {
        this.state.loop();
    }

    public void render(Graphics graphics) {
        this.state.render(graphics);
    }

    public void keyPressed(int keyCode) {
        state.keyPressed(keyCode);
    }

    public void keyReleased(int keyCode) {
        state.keyReleased(keyCode);
    }

    public void mouseClicked(MouseEvent event) {
        state.mouseClicked(event);
    }
}
