package org.kostuychenkov.game.gamestate.states;

import org.kostuychenkov.game.gamestate.GameState;
import org.kostuychenkov.game.gamestate.GameStateService;
import org.kostuychenkov.game.gui.WindowSettings;
import org.kostuychenkov.model.entities.Pet;
import org.kostuychenkov.model.entities.PetType;
import org.kostuychenkov.resources.Resources;
import org.kostuychenkov.service.PetFactory;
import org.kostuychenkov.service.PetService;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Состояние главного меню.
 */
public class MenuState extends GameState {

    private PetFactory factory = new PetFactory();
    private Pet pet;

    private String[] options;

    private static final String PLAY = "New pet";
    private static final String CONTINUE = "Continue";
    private static final String QUIT = "Quit";
    private static final String BACK = "Back";

    private static final String PIG = "Pig";
    private static final String CAT = "Cat";
    private static final String CHICKEN = "Chicken";

    private String[] mainMenu, subMenu;

    private int selected;
    private boolean isPetExists = false;

    public MenuState(GameStateService gameStateService, PetService petService, Pet pet) {
        super(gameStateService, petService);
        this.mainMenu = new String[]{PLAY, CONTINUE, QUIT};
        this.subMenu = new String[]{PIG, CAT, CHICKEN, BACK};

        this.options = mainMenu;
        this.selected = 0;

        this.pet = pet;
        if(pet != null) {
            isPetExists = true;
        }
    }

    @Override
    public void loop() {
    }

    /**
     * Размещаем и отрисовываем пункты меню.
     */
    @Override
    public void render(Graphics graphics) {
        graphics.setFont(WindowSettings.MAIN_MENU_FONT);
        graphics.drawImage(Resources.getImage(Resources.MAIN_MENU), 0, 0,
                            WindowSettings.WIDTH, WindowSettings.HEIGHT, null);

        for (int i = 0; i < options.length; i++) {
            if (i == this.selected) {
                graphics.setColor(WindowSettings.SELECTED_OPTION);
            } else {
                graphics.setColor(WindowSettings.OPTION);
            }
            graphics.drawString(options[i], 340, 250 + i * 60);
        }
    }

    /**
     * Перемещение по пунктам меню клавишами стрелок.
     */
    @Override
    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (this.selected > 0) this.selected--;
                break;
            case KeyEvent.VK_DOWN:
                if (this.selected < this.options.length - 1) this.selected++;
                break;
            case KeyEvent.VK_ENTER:

                String option = this.options[selected];

                if(option.equals(PLAY)) {
                    options = subMenu;
                }
                if(option.equals(BACK)) {
                    options = mainMenu;
                    this.selected = 0;
                }
                if(option.equals(CONTINUE)) {
                    if(isPetExists) {
                        if(pet.isAlive()) {
                            super.gameStateService.setState(new PlayingState(pet, gameStateService, petService));
                        }
                    }
                }
                if(option.equals(QUIT)) {
                    System.exit(0);
                }
                /*
                 * При выборе персонажа переводит игру в игровоое состояние.
                 */
                if(option.equals(PIG) || option.equals(CAT) || option.equals(CHICKEN)) {
                    Pet newPet = factory.getPet(PetType.valueOf(option.toUpperCase()));
                    super.gameStateService.setState(new PlayingState(newPet, gameStateService, petService));
                }
                break;
        }
    }

    @Override
    public void keyReleased(int keyCode) {
    }

    @Override
    protected void mouseClicked(MouseEvent event) {

    }
}
