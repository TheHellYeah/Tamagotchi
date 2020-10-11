package org.kostuychenkov.game.gamestate;

import org.kostuychenkov.service.PetService;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Абстрактный класс, представляющий игровое состояние и его поведение(отрисовка, главный луп игры, действия при нажатии клавиш)
 */
public abstract class GameState {

    protected GameStateService gameStateService;
    protected PetService petService;

    protected GameState(GameStateService gameStateService, PetService petService) {
        this.gameStateService = gameStateService;
        this.petService = petService;
    }

    protected abstract void loop();
    protected abstract void render(Graphics graphics);

    protected abstract void keyPressed(int keyCode);
    protected abstract void keyReleased(int keyCode);

    protected abstract void mouseClicked(MouseEvent event);
}
