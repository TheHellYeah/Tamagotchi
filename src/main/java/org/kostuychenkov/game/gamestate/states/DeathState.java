package org.kostuychenkov.game.gamestate.states;

import org.kostuychenkov.game.gamestate.GameStateService;
import org.kostuychenkov.game.gamestate.GameState;
import org.kostuychenkov.game.gui.WindowSettings;
import org.kostuychenkov.model.entities.Pet;
import org.kostuychenkov.resources.Resources;
import org.kostuychenkov.service.PetService;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Состояние смерти питомца.
 */
public class DeathState extends GameState {

    private Pet pet;

    public DeathState(Pet pet, GameStateService gameStateService, PetService petService) {
        super(gameStateService, petService);
        this.pet = pet;
    }

    @Override
    public void loop() {
    }

    /**
     * Отрисовка панели смерти и трупа питомца.
     */
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Resources.getImage(Resources.FLOOR), 0, 0,
                WindowSettings.WIDTH, WindowSettings.HEIGHT, null);
        pet.render(graphics);
        graphics.drawImage(Resources.getImage(Resources.GAME_OVER), WindowSettings.WIDTH/2 - 300, WindowSettings.HEIGHT/2 - 200,
                600, 300, null);
    }

    /**
     * Слушатель, при нажатии на ESC переходим в главное меню.
     */
    @Override
    public void keyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_ESCAPE) {
            super.gameStateService.setState(new MenuState(gameStateService, petService, null));
        }
    }

    @Override
    public void keyReleased(int keyCode) { }

    @Override
    protected void mouseClicked(MouseEvent event) { }
}
