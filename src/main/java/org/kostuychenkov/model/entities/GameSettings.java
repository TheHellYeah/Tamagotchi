package org.kostuychenkov.model.entities;

import org.kostuychenkov.game.gui.WindowSettings;

/**
 * Настраиваемы константы игрового процесса
 */
class GameSettings {

    static final long TIME_BEFORE_GETTING_HUNGRY =      3600000L; //1 час
    static final long TIME_BEFORE_GETTING_VERY_HUNGRY = 7200000L; //2 часа
    static final long TIME_BEFORE_STARVATION =        108000000L; //3 часа

    //Размеры иконки в px*px
    static final int BABY_PET_SIZE = 50;
    static final int TEENAGER_PET_SIZE = 100;
    static final int ADULT_PET_SIZE = 150;

    static final int MOOD_ICON_SIZE = 100;
    static final int MOOD_ICON_X = 0;
    static final int MOOD_ICON_Y = WindowSettings.HEIGHT - 130;

    static final int FOOD_SIZE = 50;

    static final int NEEDED_FOOD_AMOUNT_TO_TEENAGER = 5;
    static final int NEEDED_FOOD_AMOUNT_TO_ADULT = 10;
}
