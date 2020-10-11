package org.kostuychenkov.game.gamestate.states;

import org.kostuychenkov.game.gamestate.GameState;
import org.kostuychenkov.game.gamestate.GameStateService;
import org.kostuychenkov.game.gui.WindowSettings;
import org.kostuychenkov.model.entities.Food;
import org.kostuychenkov.model.entities.Pet;
import org.kostuychenkov.resources.Resources;
import org.kostuychenkov.service.PetService;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Состояние игры.
 */
public class PlayingState extends GameState {

    private Pet pet;
    private List<Food> foodPlaced;

    private final static int FEED_BUTTON_X = WindowSettings.WIDTH / 2 - 250;
    private final static int FEED_BUTTON_Y = WindowSettings.HEIGHT - 150;
    private final static int FEED_BUTTON_WIDTH = 300;
    private final static int FEED_BUTTON_HEIGHT = 150;

    private final static int SAVE_BUTTON_X = WindowSettings.WIDTH / 2;
    private final static int SAVE_BUTTON_Y = WindowSettings.HEIGHT - 150;
    private final static int SAVE_BUTTON_WIDTH = 300;
    private final static int SAVE_BUTTON_HEIGHT = 150;

    public PlayingState(Pet pet, GameStateService gameStateService, PetService petService) {
        super(gameStateService, petService);
        this.pet = pet;
        this.foodPlaced = new ArrayList<>();
    }

    /**
     * На каждой итерации проверяем жив ли питомец, производим изменение состояние питомца, его координат.
     * При смерти питомца переводит состояние игры на экран смерти(DeathState).
     */
    @Override
    public void loop() {
        if (pet.isAlive()) {
            pet.move();
            pet.checkHunger();
            pet.checkAge();
            pet.checkIntersectionsWithFood(foodPlaced);
        } else {
            super.gameStateService.setState(new DeathState(pet, gameStateService, petService));
        }
    }

    /**
     * Отрисовка питомца, кнопок управления, размещенной еды.
     */
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Resources.getImage(Resources.FLOOR), 0, 0,
                WindowSettings.WIDTH, WindowSettings.HEIGHT, null);

        graphics.drawImage(Resources.getImage(Resources.FEED_BUTTON), FEED_BUTTON_X, FEED_BUTTON_Y,
                                                FEED_BUTTON_WIDTH, FEED_BUTTON_HEIGHT, null);
        graphics.drawImage(Resources.getImage(Resources.SAVE_BUTTON), SAVE_BUTTON_X, SAVE_BUTTON_Y,
                                                SAVE_BUTTON_WIDTH, SAVE_BUTTON_HEIGHT, null);
        pet.render(graphics);
        foodPlaced.forEach(food -> food.render(graphics));
    }

    /**
     * Перемещение питомца клавишами WASD.
     */
    @Override
    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                pet.setMovingUp(true);
                break;
            case KeyEvent.VK_A:
                pet.setMovingLeft(true);
                break;
            case KeyEvent.VK_S:
                pet.setMovingDown(true);
                break;
            case KeyEvent.VK_D:
                pet.setMovingRight(true);
                break;
        }
    }

    @Override
    public void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                pet.setMovingUp(false);
                break;
            case KeyEvent.VK_A:
                pet.setMovingLeft(false);
                break;
            case KeyEvent.VK_S:
                pet.setMovingDown(false);
                break;
            case KeyEvent.VK_D:
                pet.setMovingRight(false);
                break;
        }
    }

    /**
     * Метод отлавливает нажатия на кнопки управления. При нажатии на Save переводит игру в главное меню и сохраняет питомца,
     * при нажатии на Feed случайно размещает еду на игровое поле.
     */
    @Override
    protected void mouseClicked(MouseEvent event) {
        int mx = event.getX();
        int my = event.getY();
        if(mx >= FEED_BUTTON_X && mx <= FEED_BUTTON_X + FEED_BUTTON_WIDTH &&
                my >= FEED_BUTTON_Y && my <= FEED_BUTTON_Y + FEED_BUTTON_HEIGHT) {

            placeFood();
        }
        if(mx >= SAVE_BUTTON_X && mx <= SAVE_BUTTON_X + SAVE_BUTTON_WIDTH &&
                my >= SAVE_BUTTON_Y && my <= SAVE_BUTTON_Y + SAVE_BUTTON_HEIGHT) {

            petService.save(pet);
            super.gameStateService.setState(new MenuState(gameStateService, petService, pet));
        }
    }

    /**
     * Создание еды и придание ей рандомных координат.
     */
    private void placeFood() {
        Food food = new Food(pet.getFoodType());
        food.setRandomPosition(WindowSettings.WIDTH, WindowSettings.HEIGHT - 190);
        foodPlaced.add(food);
    }
}
