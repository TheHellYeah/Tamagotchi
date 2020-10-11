package org.kostuychenkov.model.entities;

import org.kostuychenkov.game.engine.AudioPlayer;
import org.kostuychenkov.resources.Resources;

import java.awt.*;

public class Cat extends Pet {

    public Cat() {
        super.speed = 12;
        super.image = Resources.getImage(Resources.CAT_RIGHT);
        super.foodType = Resources.getImage(Resources.CAT_FOOD);
    }

    @Override
    protected void playDeathSound() {
        AudioPlayer.playMusic(Resources.CAT_DEAD, false);
    }

    @Override
    protected Image getMovingLeftImage() {
        return Resources.getImage(Resources.CAT_LEFT);
    }

    @Override
    protected Image getMovingRightImage() {
        return Resources.getImage(Resources.CAT_RIGHT);
    }


}
