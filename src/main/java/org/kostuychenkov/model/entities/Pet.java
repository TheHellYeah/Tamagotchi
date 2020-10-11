package org.kostuychenkov.model.entities;

import org.kostuychenkov.game.engine.AudioPlayer;
import org.kostuychenkov.game.gui.WindowSettings;
import org.kostuychenkov.resources.Resources;

import java.awt.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Абстрактный класс питомца.
 */
public abstract class Pet extends Rectangle {

    protected int speed;
    protected Image image;
    protected Image foodType;

    private int foodEaten;
    private PetAge age;
    private LifeState state;
    private Date lastTimeAte;
    private Image mood;

    private boolean movingUp;
    private boolean movingDown;
    private boolean movingLeft;
    private boolean movingRight;

    public Pet() {
        super(GameSettings.BABY_PET_SIZE, GameSettings.BABY_PET_SIZE);

        this.age = PetAge.BABY;

        this.mood = Resources.getImage(Resources.MOOD_HAPPY);
        this.state = LifeState.ALIVE;

        this.lastTimeAte = new Date();
        this.foodEaten = 0;
    }

    /**
     * Звук при смерти питомца.
     */
    protected abstract void playDeathSound();

    /**
     * Изменение характеристик питомца при поедании еды, проигрывание соответствующего звука.
     */
    public void eat() {
        this.lastTimeAte = new Date();
        this.foodEaten++;
        this.setHappy();
        AudioPlayer.playMusic(Resources.EAT, false);
    }

    /**
     * Движение питомца, при движении влево или вправо меняется также спрайт питомца на зеркальный.
     */
    public void move() {
        if (movingUp) {
            super.y -= this.speed;
        }
        if (movingDown) {
            super.y += this.speed;
        }
        if (movingLeft) {
            super.x -= this.speed;
            this.image = getMovingLeftImage();
        }
        if (movingRight) {
            super.x += this.speed;
            this.image = getMovingRightImage();
        }
        checkCollision();
    }

    /**
     * Отрисовка спрайта питомца и его состояния.
     */
    public void render(Graphics graphics) {
        graphics.drawImage(image, super.x, super.y, super.width, super.height, null);
        graphics.drawImage(mood, GameSettings.MOOD_ICON_X, GameSettings.MOOD_ICON_Y,
                GameSettings.MOOD_ICON_SIZE, GameSettings.MOOD_ICON_SIZE, null);
    }

    public boolean isAlive() {
        return this.state.equals(LifeState.ALIVE);
    }

    /**
     * Проверяет дату последней кормежки питомца.
     * При долгом отсутсвии еды, у питомца ухудшается настроение, также изменяется иконка
     * настроения на статус-баре.
     * После достижения критической точки, питомец умирает(спрайт питомца меняется на мертвого).
     */
    public void checkHunger() {
        Date now = new Date();
        long timeAfterLastLunch = now.getTime() - lastTimeAte.getTime();

        if (timeAfterLastLunch > GameSettings.TIME_BEFORE_GETTING_HUNGRY) {
            this.setNeutral();

            if (timeAfterLastLunch > GameSettings.TIME_BEFORE_GETTING_VERY_HUNGRY) {
                this.setSad();

                if (timeAfterLastLunch > GameSettings.TIME_BEFORE_STARVATION) {
                    this.setDead();
                }
            }
        }
    }

    /**
     * Проверяет возраст питомца(питомец растет после определенного количества кормежек).
     * При росте питомца уменьшается его скорость и увеличивается размер.
     */
    public void checkAge() {
        if (this.age.equals(PetAge.ADULT)) return;

        if (readyToGrowTeenager())  this.setTeenager();
        else if (readyToGrowAdult())   this.setAdult();
    }

    /**
     * Проверка, не выходит ли питомец за границы комнаты.
     * Для проверки правой и нижней стен используем не исходные координаты x и y
     * (отсчет координат идет от верхнего левого угла (x,y) = (0,0)),
     * а координаты правой и нижней сторон хитбокса питомца(x+width, y+height).
     * Для нижней границы взято WindowSettings.HEIGHT - 140, чтобы не наезжать питомцем на HUD.
     */
    private void checkCollision() {
        if (this.x < 0) this.x = 0;
        if (this.y < 0) this.y = 0;
        if (this.x + super.width > WindowSettings.WIDTH) this.x = WindowSettings.WIDTH - super.width;
        if (this.y + super.height > WindowSettings.HEIGHT - 140) this.y = WindowSettings.HEIGHT - 140 - super.height;
    }

    /**
     * Проверка пересечения питомца с размещенной едой на экране.
     * Если пересечение обнаружено, еда убирается с экрана, и вызывается метод eat() у питомца.
     */
    public void checkIntersectionsWithFood(List<Food> foodPlaced) {
        Iterator<Food> iterator = foodPlaced.iterator();
        while (iterator.hasNext()) {
            Food food = iterator.next();
            Rectangle intersection = this.intersection(food);
            if (!intersection.isEmpty()) {
                this.eat();
                iterator.remove();
            }
        }
    }

    private void setDead() {
        this.state = LifeState.DEAD;
        this.image = Resources.getImage(Resources.DEAD);
        playDeathSound();
    }

    private void setSad() {
        this.mood = Resources.getImage(Resources.MOOD_SAD);
    }

    private void setNeutral() {
        this.mood = Resources.getImage(Resources.MOOD_NEUTRAL);
    }

    private void setHappy() {
        this.mood = Resources.getImage(Resources.MOOD_HAPPY);
    }

    private void setTeenager() {
        this.age = PetAge.TEENAGER;
        super.width = GameSettings.TEENAGER_PET_SIZE;
        super.height = GameSettings.TEENAGER_PET_SIZE;
        this.speed--;
    }

    private void setAdult() {
        this.age = PetAge.ADULT;
        super.width = GameSettings.ADULT_PET_SIZE;
        super.height = GameSettings.ADULT_PET_SIZE;
        this.speed--;
    }

    private boolean readyToGrowTeenager() {
        return foodEaten >= GameSettings.NEEDED_FOOD_AMOUNT_TO_TEENAGER &&
                foodEaten < GameSettings.NEEDED_FOOD_AMOUNT_TO_ADULT &&
                this.age != PetAge.TEENAGER;
    }

    private boolean readyToGrowAdult() {
        return foodEaten >= GameSettings.NEEDED_FOOD_AMOUNT_TO_ADULT;
    }

    public Image getFoodType() {
        return foodType;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    // Методы возвращающие левый/правый спрайт питомца(необходимы при изменении направления движения)
    protected abstract Image getMovingLeftImage();
    protected abstract Image getMovingRightImage();

    public void setFoodEaten(int foodEaten) {
        this.foodEaten = foodEaten;
    }

    public void setLastTimeAte(long milliseconds) {
        this.lastTimeAte = new Date(milliseconds);
    }

    public Date getLastTimeAte() {
        return this.lastTimeAte;
    }

    public int getFoodEaten() {
        return this.foodEaten;
    }

    public PetType getPetType() {
        if(this.getClass() == Cat.class) {
            return PetType.CAT;
        }
        if(this.getClass() == Pig.class) {
            return PetType.PIG;
        }
        if(this.getClass() == Chicken.class) {
            return PetType.CHICKEN;
        }
        return null;
    }
}
