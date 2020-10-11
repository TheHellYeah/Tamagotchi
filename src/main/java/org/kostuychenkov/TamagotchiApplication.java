package org.kostuychenkov;

import org.kostuychenkov.game.engine.Engine;
import org.kostuychenkov.resources.Resources;

import javax.swing.*;

public class TamagotchiApplication {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Resources.load();
            new Engine().start();
        });
    }
}
