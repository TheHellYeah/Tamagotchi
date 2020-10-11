package org.kostuychenkov.model.entities;

import org.kostuychenkov.game.engine.AudioPlayer;
import org.kostuychenkov.resources.Resources;

import java.awt.*;

public class Pig extends Pet {

    public Pig() {
        super.speed = 8;
        super.image = Resources.getImage(Resources.PIG_RIGHT);
        super.foodType = Resources.getImage(Resources.PIG_FOOD);
    }

    @Override
    protected void playDeathSound() {
        AudioPlayer.playMusic(Resources.PIG_DEAD, false);
    }

    @Override
    protected Image getMovingLeftImage() {
        return Resources.getImage(Resources.PIG_LEFT);
    }

    @Override
    protected Image getMovingRightImage() {
        return Resources.getImage(Resources.PIG_RIGHT);
    }
}
