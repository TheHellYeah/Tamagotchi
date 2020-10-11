package org.kostuychenkov.model.entities;

import org.kostuychenkov.game.engine.AudioPlayer;
import org.kostuychenkov.resources.Resources;

import java.awt.*;

public class Chicken extends Pet {

    public Chicken() {
        super.speed = 10;
        super.image = Resources.getImage(Resources.CHICKEN_RIGHT);
        super.foodType =  Resources.getImage(Resources.CHICKEN_FOOD);
    }

    @Override
    protected void playDeathSound() {
        AudioPlayer.playMusic(Resources.CHICKEN_DEAD, false);
    }

    @Override
    protected Image getMovingLeftImage() {
        return Resources.getImage(Resources.CHICKEN_LEFT);
    }

    @Override
    protected Image getMovingRightImage() {
        return Resources.getImage(Resources.CHICKEN_RIGHT);
    }
}
