package org.kostuychenkov.resources;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, отвечающий за загрузку ресурсов(спрайтов и т.д.), и получения доступа к ним.
 */
public class Resources {

    private static final String SPRITES_PATH = "src/main/resources/images";
    private static final List<ImageIcon> textures = new ArrayList<>();

    /**
     * Константы необходимы для распознования изображений в пределах всей программы и получения доступа к изображениям по данным индексам.
     */
    public static final int FLOOR = 0;
    public static final int CAT_RIGHT = 1;
    public static final int PIG_RIGHT = 2;
    public static final int CHICKEN_RIGHT = 3;
    public static final int MAIN_MENU = 4;
    public static final int MOOD_SAD = 5;
    public static final int MOOD_HAPPY = 6;
    public static final int MOOD_NEUTRAL = 7;
    public static final int DEAD = 8;
    public static final int FEED_BUTTON = 9;
    public static final int SAVE_BUTTON = 10;
    public static final int CAT_FOOD = 11;
    public static final int PIG_FOOD = 12;
    public static final int CHICKEN_FOOD = 13;
    public static final int CAT_LEFT = 14;
    public static final int PIG_LEFT = 15;
    public static final int CHICKEN_LEFT = 16;
    public static final int GAME_OVER = 17;

    /**
     * Константы, отвечающие за расположение определнных звуковых файлов.
     */
    public static final String MAIN_THEME = "/main.wav";
    public static final String CAT_DEAD = "/cat_dead.wav";
    public static final String PIG_DEAD = "/pig_dead.wav";
    public static final String CHICKEN_DEAD = "/chicken_dead.wav";
    public static final String EAT = "/eat.wav";

    /**
     * Загрузка изображний(если необходимо добавить новые спрайты,
     * то стоит делать это в КОНЦЕ метода, не нарушая существующий порядок индексов листа textures).
     */
    public static void load() {
        textures.add(new ImageIcon(SPRITES_PATH + "/floor.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/cat_right.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/pig_right.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/chicken_right.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/main_background.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/mood_sad.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/mood_happy.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/mood_neutral.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/dead.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/feed_button.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/save_button.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/cat_food.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/pig_food.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/chicken_food.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/cat_left.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/pig_left.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/chicken_left.png"));
        textures.add(new ImageIcon(SPRITES_PATH + "/game_over.png"));
    }

    public static Image getImage(int index) {
        return textures.get(index).getImage();
    }
}
