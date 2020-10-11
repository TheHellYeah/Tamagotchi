package org.kostuychenkov.model.entities;

import java.awt.*;
import java.util.Random;

/**
 * Класс, отвечающий за еду.
 */
public class Food extends Rectangle {

    private Image image;

    public Food(Image image) {
        super(GameSettings.FOOD_SIZE, GameSettings.FOOD_SIZE);
        this.image = image;
    }

    /**
     * Случайно размещает еду в пределах прямоугольника с границами от (0,0) до (roomWidth, roomHeight).
     */
    public void setRandomPosition(int roomWidth, int roomHeight) {
        Random random = new Random();
        super.x = random.nextInt(roomWidth);
        super.y = random.nextInt(roomHeight);
    }

    /**
     * Отрисовка еды
     */
    public void render(Graphics graphics) {
        graphics.drawImage(image, super.x, super.y, super.width, super.height, null);
    }
}
